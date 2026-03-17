package com.merkle.oss.magnolia.definition.custom.itemprovider;

import info.magnolia.cms.security.AccessManager;
import info.magnolia.cms.security.Permission;
import info.magnolia.context.Context;
import info.magnolia.ui.api.i18n.I18NAuthoringSupport;
import info.magnolia.ui.editor.ComplexPropertyDefinition;
import info.magnolia.ui.editor.JcrChildNodeProvider;
import info.magnolia.ui.editor.JcrChildNodeProviderDefinition;

import java.lang.invoke.MethodHandles;
import java.util.Optional;

import javax.jcr.Node;
import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.merkle.oss.magnolia.powernode.PowerNode;
import com.merkle.oss.magnolia.powernode.PowerNodeService;

import jakarta.inject.Inject;
import jakarta.inject.Provider;

public class PermissionRespectingJcrChildNodeProvider<D extends PermissionRespectingJcrChildNodeProvider.Definition> extends JcrChildNodeProvider<D> {
    private static final Logger LOG = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private final Provider<Context> contextProvider;
    private final PowerNodeService powerNodeService;

    @Inject
    public PermissionRespectingJcrChildNodeProvider(
            final D definition,
            final ComplexPropertyDefinition<Node> associatedPropertyDefinition,
            final I18NAuthoringSupport<Node> i18NAuthoringSupport,
            final Provider<Context> contextProvider,
            final PowerNodeService powerNodeService
    ) {
        super(definition, associatedPropertyDefinition, i18NAuthoringSupport);
        this.contextProvider = contextProvider;
        this.powerNodeService = powerNodeService;
    }

    @Override
    protected Optional<Node> getSubNode(final Node parentNode, final String subNodeName) throws RepositoryException {
        if (hasWritePermissions(powerNodeService.convertToPowerNode(parentNode))) {
            return super.getSubNode(parentNode, subNodeName);
        }
        return Optional.empty();
    }

    private boolean hasWritePermissions(final PowerNode parentNode) {
        try {
            final AccessManager ami = contextProvider.get().getAccessManager(parentNode.getSession().getWorkspace().getName());
            return ami.isGranted(parentNode.getPath(), Permission.ADD);
        } catch (final Exception e) {
            LOG.error("Failed to evaluate if user has permissions for {} {} ! returning false", parentNode.getSession().getWorkspace().getName(), parentNode.getPath(), e);
        }
        return false;
    }

    public static class Definition extends JcrChildNodeProviderDefinition {
        public Definition() {
            setImplementationClass((Class) PermissionRespectingJcrChildNodeProvider.class);
        }
    }
}
