/* 
 * Copyright 2013-2019 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.metamodel.impl.expert.standard.links.impl.creation;

import java.util.Collection;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.impl.expert.standard.links.ILinkExpert;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Attribute;
import org.modelio.metamodel.uml.statik.BindableInstance;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Collaboration;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Operation;
import org.modelio.metamodel.uml.statik.Parameter;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Creation expert for {@link Binding}
 * <p>
 * 
 * Bindings can be traced:
 * <ul>
 * <li>From a collaboration use to a "feature" owned by the same classifier/collaboration as the collaboration use.</li>
 * <li>From a "feature" owned by the same classifier/collaboration as the collaboration use to a role of the used collaboration.</li>
 * </ul>
 */
@objid ("7e94dfdb-1eb2-11e2-8009-002564c97630")
public class BindingCreationExpert extends DefaultDelegatingLinkExpert {
    @objid ("7e94dfe0-1eb2-11e2-8009-002564c97630")
    @Override
    public boolean canLink(MClass linkMetaclass, final MObject from, final MObject to) {
        if (!canSource(linkMetaclass, from.getMClass())) {
            return false;
        }
        
        if (from instanceof CollaborationUse) {
            // Binding from a collaboration use to a represented feature.
            // The collaboration use and the represented feature must be owned by:
            // - the same classifier.
            // - the same collaboration
        
            final MObject fromOwner = getClassifierOwner(from);
            final MObject toOwner = getClassifierOwner(to);
            // Same Classifier owner => return true
            if (fromOwner != null && toOwner != null && fromOwner.equals(toOwner)) {
                return true;
            }
        
            // Mantis 11428 - Same Collaboration owner => return true
            final MObject fromCollabOwner = getCollaborationOwner(from);
            final MObject toCollabOwner = getCollaborationOwner(to);
        
            return (fromCollabOwner != null && toCollabOwner != null && fromCollabOwner.equals(toCollabOwner));
        } else {
            // Binding from a represented feature to a collaboration role.
            // - the source must be a "feature" owned by the collaboration use owner
            // - the target must be owned by the used Collaboration
        
            final CollaborationUse collabUse = findCollabUse(from, to);
            if (collabUse == null) {
                return false;
            }
        
            final Collaboration usedCollab = collabUse.getType();
        
            final Collaboration collaboration = getCollaborationOwner(to);
            if (collaboration == null || !collaboration.equals(usedCollab)) {
                return false;
            }
        
            final MObject fromOwner = getClassifierOwner(from);
            final MObject collabUseOwner = getClassifierOwner(collabUse);
        
            return (fromOwner != null && collabUseOwner != null && fromOwner.equals(collabUseOwner));
        }
    }

    @objid ("7e94dfea-1eb2-11e2-8009-002564c97630")
    @Override
    public boolean canSource(final MClass linkMetaclass, final MClass fromMetaclass) {
        if (CollaborationUse.class.isAssignableFrom(fromMetaclass.getJavaInterface())) {
            return true;
        }
        
        if (Attribute.class.isAssignableFrom(fromMetaclass.getJavaInterface())
                || AssociationEnd.class.isAssignableFrom(fromMetaclass.getJavaInterface())
                || BindableInstance.class.isAssignableFrom(fromMetaclass.getJavaInterface())
                || ConnectorEnd.class.isAssignableFrom(fromMetaclass.getJavaInterface())
                || Parameter.class.isAssignableFrom(fromMetaclass.getJavaInterface())) {
            return true;
        }
        return false;
    }

    @objid ("7e94dff4-1eb2-11e2-8009-002564c97630")
    @Override
    public boolean canSource(final MObject fromElement, final MObject ownerElement) {
        if (fromElement instanceof CollaborationUse) {
            return (ownerElement == null || ownerElement.equals(fromElement));
        } else if (fromElement instanceof Attribute || fromElement instanceof AssociationEnd
                || fromElement instanceof BindableInstance || fromElement instanceof ConnectorEnd) {
            // Test the binding start from a feature owned by a classifier that has a collaboration use
            // If owner is specified, it must be a collaboration use and the link start classifier must own it.
            final Classifier fromOwner = (Classifier) getClassifierOwner(fromElement);
            if (fromOwner == null) {
                return false;
            } else if (ownerElement == null) {
                return (fromOwner.getOwnedCollaborationUse().size() > 0);
            } else if (ownerElement instanceof CollaborationUse) {
                return fromOwner.getOwnedCollaborationUse().contains(ownerElement);
            } else {
                return false;
            }
        } else if (fromElement instanceof Parameter) {
            // Test the binding start from a parameter owned by an operation that has a collaboration use
            // If owner is specified, it must be a collaboration use and the link start operation must own it.
            final Operation op = (Operation) fromElement.getCompositionOwner();
            if (op == null) {
                return false;
            } else if (ownerElement == null) {
                return (op.getOwnedCollaborationUse().size() > 0);
            } else if (ownerElement instanceof CollaborationUse) {
                return op.getOwnedCollaborationUse().contains(ownerElement);
            } else {
                return false;
            }
        
        } else {
            return false;
        }
    }

    /**
     * Get the Classifier owning the given element
     * 
     * @param e an element
     * @return The classifier owner or null.
     */
    @objid ("7e94e01f-1eb2-11e2-8009-002564c97630")
    private static MObject getClassifierOwner(final MObject e) {
        MObject ret = e.getCompositionOwner();
        
        while (ret != null && !(ret instanceof Classifier)) {
            ret = ret.getCompositionOwner();
        }
        return ret;
    }

    /**
     * Get the collaboration owning the given element, or null if the element is not owned by a collaboration.
     * @param e
     * an element
     * 
     * @return The owning collaboration or null.
     */
    @objid ("7e97413f-1eb2-11e2-8009-002564c97630")
    private static Collaboration getCollaborationOwner(final MObject role) {
        MObject container = role.getCompositionOwner();
        while (container != null) {
            if (container instanceof Collaboration) {
                return (Collaboration) container;
            } else if (container instanceof NameSpace) {
                container = null;
            } else {
                container = container.getCompositionOwner();
            }
        }
        return null;
    }

    /**
     * Guess the collaboration use from the represented feature and the role.
     * 
     * @param feature the represented feature
     * @param role the role
     * @return the found collaboration use or null if none.
     */
    @objid ("7e974146-1eb2-11e2-8009-002564c97630")
    private static CollaborationUse findCollabUse(final MObject feature, final MObject role) {
        final Collaboration collab = getCollaborationOwner(role);
        if (collab == null) {
            return null;
        }
        
        final Collection<CollaborationUse> uses = getCollabUsesOf(feature);
        if (uses == null) {
            return null;
        }
        
        for (CollaborationUse u : uses) {
            if (collab.equals(u.getType())) {
                return u;
            }
        }
        return null;
    }

    /**
     * Get the collaboration uses accessible from the given represented feature.
     * 
     * @param feature a represented feature
     * @return accessible collaboration uses
     */
    @objid ("7e97414f-1eb2-11e2-8009-002564c97630")
    private static Collection<CollaborationUse> getCollabUsesOf(final MObject feature) throws IllegalArgumentException {
        MObject container = feature.getCompositionOwner();
        while (container != null) {
            if (container instanceof NameSpace) {
                return ((NameSpace) container).getOwnedCollaborationUse();
            } else if (container instanceof Operation) {
                return ((Operation) container).getOwnedCollaborationUse();
            } else {
                container = container.getCompositionOwner();
            }
        }
        return null;
    }

    @objid ("b40d3b76-ded4-4f93-971c-80a43c29f0cb")
    public BindingCreationExpert(ILinkExpert defaultExpert) {
        super(defaultExpert);
    }

}
