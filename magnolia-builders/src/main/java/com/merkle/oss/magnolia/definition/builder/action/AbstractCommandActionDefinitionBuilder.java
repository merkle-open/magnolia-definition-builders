package com.merkle.oss.magnolia.definition.builder.action;

import info.magnolia.ui.api.action.CommandActionDefinition;
import info.magnolia.ui.api.availability.AvailabilityDefinition;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jakarta.annotation.Nullable;

public class AbstractCommandActionDefinitionBuilder<D extends CommandActionDefinition, B extends AbstractCommandActionDefinitionBuilder<D, B>> extends AbstractActionDefinitionBuilder<D, B>{
    @Nullable
    private String command;
    @Nullable
    private String catalog;
    @Nullable
    private Boolean asynchronous;
    @Nullable
    private Integer delay;
    @Nullable
    private Integer timeToWait;
    @Nullable
    private Boolean notifyUser;
    @Nullable
    private Boolean alwaysShowSuccessMessage;
    @Nullable
    private Boolean bulk;
    @Nullable
    private Map<String, Object> params;
    @Nullable
    private AvailabilityDefinition availability;

    public AbstractCommandActionDefinitionBuilder() {}
    public AbstractCommandActionDefinitionBuilder(final D definition) {
        Optional.ofNullable(definition.getCommand()).ifPresent(this::command);
        Optional.ofNullable(definition.getCatalog()).ifPresent(this::catalog);
        asynchronous(definition.isAsynchronous());
        delay(definition.getDelay());
        timeToWait(definition.getTimeToWait());
        notifyUser(definition.isNotifyUser());
        alwaysShowSuccessMessage(definition.isAlwaysShowSuccessMessage());
        bulk(definition.isBulk());
        Optional.ofNullable(definition.getParams()).ifPresent(this::params);
        Optional.ofNullable(definition.getAvailability()).ifPresent(this::availability);
        Optional.ofNullable(definition.getLabel()).ifPresent(this::label);
    }

    public B command(final String command){
        this.command = command;
        return self();
    }
    public B catalog(final String catalog){
        this.catalog = catalog;
        return self();
    }
    public B asynchronous(final boolean asynchronous){
        this.asynchronous = asynchronous;
        return self();
    }
    public B delay(final int delay){
        this.delay = delay;
        return self();
    }
    public B timeToWait(final int timeToWait){
        this.timeToWait = timeToWait;
        return self();
    }
    public B notifyUser(final boolean notifyUser){
        this.notifyUser = notifyUser;
        return self();
    }
    public B alwaysShowSuccessMessage(final boolean alwaysShowSuccessMessage){
        this.alwaysShowSuccessMessage = alwaysShowSuccessMessage;
        return self();
    }
    public B bulk(final boolean bulk){
        this.bulk = bulk;
        return self();
    }
    public B param(final String key, Object value){
        return params(Stream.concat(
                Stream.ofNullable(params).map(Map::entrySet).flatMap(Collection::stream),
                Stream.of(Map.entry(key, value))
        ).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }
    public B params(final Map<String, Object> params){
        this.params = params;
        return self();
    }
    public B availability(final AvailabilityDefinition availability){
        this.availability = availability;
        return self();
    }

    @SuppressWarnings("unchecked")
    protected B self() {
        return (B) this;
    }

    protected void populate(final D definition, final String name) {
        super.populate(definition, name);
        Optional.ofNullable(command).ifPresent(definition::setCommand);
        Optional.ofNullable(catalog).ifPresent(definition::setCatalog);
        Optional.ofNullable(asynchronous).ifPresent(definition::setAsynchronous);
        Optional.ofNullable(delay).ifPresent(definition::setDelay);
        Optional.ofNullable(timeToWait).ifPresent(definition::setTimeToWait);
        Optional.ofNullable(notifyUser).ifPresent(definition::setNotifyUser);
        Optional.ofNullable(alwaysShowSuccessMessage).ifPresent(definition::setAlwaysShowSuccessMessage);
        Optional.ofNullable(bulk).ifPresent(definition::setBulk);
        Optional.ofNullable(params).ifPresent(definition::setParams);
        Optional.ofNullable(availability).ifPresent(definition::setAvailability);
    }
}