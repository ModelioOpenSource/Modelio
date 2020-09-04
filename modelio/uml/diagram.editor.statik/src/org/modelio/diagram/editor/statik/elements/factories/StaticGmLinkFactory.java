/* 
 * Copyright 2013-2020 Modeliosoft
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

package org.modelio.diagram.editor.statik.elements.factories;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.diagram.editor.statik.elements.abstraction.GmAbstraction;
import org.modelio.diagram.editor.statik.elements.association.GmAssociation;
import org.modelio.diagram.editor.statik.elements.associationclass.GmClassAssociationLink;
import org.modelio.diagram.editor.statik.elements.bindinglink.GmBindingLink;
import org.modelio.diagram.editor.statik.elements.collabuselink.GmCollabUseLink;
import org.modelio.diagram.editor.statik.elements.componentRealization.GmComponentRealization;
import org.modelio.diagram.editor.statik.elements.connector.GmConnectorLink;
import org.modelio.diagram.editor.statik.elements.constraint.GmConstraintLink;
import org.modelio.diagram.editor.statik.elements.elementRealization.GmElementRealization;
import org.modelio.diagram.editor.statik.elements.elementimport.GmElementImport;
import org.modelio.diagram.editor.statik.elements.generalization.GmGeneralization;
import org.modelio.diagram.editor.statik.elements.informationflowlink.GmInformationFlowLink;
import org.modelio.diagram.editor.statik.elements.instancelink.GmInstanceLink;
import org.modelio.diagram.editor.statik.elements.naryassoc.GmNAssocEndLink;
import org.modelio.diagram.editor.statik.elements.naryconnector.GmNConnectorEndLink;
import org.modelio.diagram.editor.statik.elements.narylink.GmNLinkEndLink;
import org.modelio.diagram.editor.statik.elements.packageimport.GmPackageImport;
import org.modelio.diagram.editor.statik.elements.packagemerge.GmPackageMerge;
import org.modelio.diagram.editor.statik.elements.providedinterface.GmProvidedInterfaceLink;
import org.modelio.diagram.editor.statik.elements.raisedexception.GmRaisedException;
import org.modelio.diagram.editor.statik.elements.realization.GmInterfaceRealization;
import org.modelio.diagram.editor.statik.elements.requiredinterface.GmRequiredInterfaceLink;
import org.modelio.diagram.editor.statik.elements.substitution.GmSubstitution;
import org.modelio.diagram.editor.statik.elements.templatebinding.GmTemplateBinding;
import org.modelio.diagram.elements.core.link.GmLink;
import org.modelio.diagram.elements.core.model.IGmDiagram;
import org.modelio.diagram.elements.core.model.factory.IGmLinkFactory;
import org.modelio.diagram.elements.umlcommon.usage.GmUsage;
import org.modelio.diagram.persistence.IPersistent;
import org.modelio.diagram.persistence.IPersistentMigrator;
import org.modelio.metamodel.uml.informationFlow.InformationFlow;
import org.modelio.metamodel.uml.infrastructure.Abstraction;
import org.modelio.metamodel.uml.infrastructure.Substitution;
import org.modelio.metamodel.uml.infrastructure.Usage;
import org.modelio.metamodel.uml.statik.Association;
import org.modelio.metamodel.uml.statik.AssociationEnd;
import org.modelio.metamodel.uml.statik.Binding;
import org.modelio.metamodel.uml.statik.ClassAssociation;
import org.modelio.metamodel.uml.statik.CollaborationUse;
import org.modelio.metamodel.uml.statik.ComponentRealization;
import org.modelio.metamodel.uml.statik.Connector;
import org.modelio.metamodel.uml.statik.ConnectorEnd;
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.metamodel.uml.statik.ElementRealization;
import org.modelio.metamodel.uml.statik.Generalization;
import org.modelio.metamodel.uml.statik.InterfaceRealization;
import org.modelio.metamodel.uml.statik.Link;
import org.modelio.metamodel.uml.statik.LinkEnd;
import org.modelio.metamodel.uml.statik.NaryAssociationEnd;
import org.modelio.metamodel.uml.statik.NaryConnectorEnd;
import org.modelio.metamodel.uml.statik.NaryLinkEnd;
import org.modelio.metamodel.uml.statik.PackageImport;
import org.modelio.metamodel.uml.statik.PackageMerge;
import org.modelio.metamodel.uml.statik.ProvidedInterface;
import org.modelio.metamodel.uml.statik.RaisedException;
import org.modelio.metamodel.uml.statik.RequiredInterface;
import org.modelio.metamodel.uml.statik.TemplateBinding;
import org.modelio.metamodel.visitors.DefaultModelVisitor;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.mapi.MRef;

/**
 * Static diagram specific implementation of {@link IGmLinkFactory}.
 * <p>
 * This particular implementation:
 * <ul>
 * <li>does not support cascading</li>
 * <li>only processes static diagram specific elements</li>
 * </ul>
 * </p>
 */
@objid ("36b91937-55b7-11e2-877f-002564c97630")
public class StaticGmLinkFactory implements IGmLinkFactory {
    @objid ("36b91947-55b7-11e2-877f-002564c97630")
    @Override
    public GmLink create(IGmDiagram diagram, MObject linkElement) {
        return (GmLink) linkElement.accept(new ImplVisitor(diagram));
    }

    @objid ("6e0e563d-c3f9-4b12-b835-e0703ff8476a")
    @Override
    public Class<? extends IPersistent> resolveClass(String namespace) {
        try {
            if (namespace.startsWith("org.modelio.diagram.elements")) {
                // Deal with a few GMs from diagram.elements that were moved into diagram.static
                switch (namespace) {
                case "org.modelio.diagram.elements.umlcommon.abstraction.GmAbstraction":
                    return GmAbstraction.class; // 3.5 -> 3.6 migration
                case "org.modelio.diagram.elements.umlcommon.constraint.GmConstraintLink":
                    return GmConstraintLink.class; // 3.5 -> 3.6 migration
                case "org.modelio.diagram.elements.umlcommon.elementRealization.GmElementRealization":
                    return GmElementRealization.class; // 3.5 -> 3.6 migration
                default:
                    return null;
                }
            } else if (namespace.startsWith("org.modelio.diagram.editor.statik")) {
                Class<?> clazz = Class.forName(namespace);
                if (clazz != null) {
                    return clazz.asSubclass(IPersistent.class);
                }
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("9cf6a769-8449-4303-846f-f9e106b5b9f4")
    @Override
    public Class<? extends IPersistentMigrator> resolveMigratorClass(String classNamespace) {
        try {
            Class<?> clazz = Class.forName(classNamespace);
            if (clazz != null) {
                return clazz.asSubclass(IPersistentMigrator.class);
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Class not found, return null
        }
        return null;
    }

    @objid ("45b52e58-a9a0-4e1d-ad67-7a2913692cbd")
    @Override
    public Class<? extends Enum<?>> resolveEnumClass(String enumNamespace) {
        try {
            Class<?> clazz = Class.forName(enumNamespace);
            if (clazz != null && clazz.isEnum()) {
                return (Class<? extends Enum<?>>) clazz;
            }
        } catch (@SuppressWarnings ("unused") ClassNotFoundException | ClassCastException e) {
            // Enum not found, return null
        }
        return null;
    }

    /**
     * visitor class for the implementation of the links.
     */
    @objid ("36ba9fb9-55b7-11e2-877f-002564c97630")
    private class ImplVisitor extends DefaultModelVisitor {
        @objid ("a7c8ba69-55c2-11e2-9337-002564c97630")
        private IGmDiagram diagram;

        @objid ("36ba9fc0-55b7-11e2-877f-002564c97630")
        public ImplVisitor(IGmDiagram diagram) {
            this.diagram = diagram;
        }

        @objid ("c47386ef-ea96-4641-a298-ecc60cec98c5")
        @Override
        public Object visitAbstraction(Abstraction obj) {
            return new GmAbstraction(this.diagram, obj, new MRef(obj));
        }

        @objid ("1349b248-97d8-43a9-bd0f-92c50cf59c74")
        @Override
        public Object visitAssociation(Association association) {
            return new GmAssociation(this.diagram,
                                association.getEnd().get(0),
                                new MRef(association.getEnd().get(0)),
                                new MRef(association));
        }

        @objid ("828f5907-df5e-41e6-b189-50016b10fbfe")
        @Override
        public Object visitAssociationEnd(AssociationEnd role) {
            return new GmAssociation(this.diagram,
                                role,
                                new MRef(role),
                                new MRef(role.getAssociation()));
        }

        @objid ("67587c9e-5676-4d39-87b4-6e8a71b6b382")
        @Override
        public Object visitBinding(Binding theBinding) {
            return new GmBindingLink(this.diagram, theBinding, new MRef(theBinding));
        }

        @objid ("36bdad07-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitClassAssociation(final ClassAssociation theAssoc) {
            return new GmClassAssociationLink(this.diagram, theAssoc, new MRef(theAssoc));
        }

        @objid ("36ba9fdb-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitCollaborationUse(final CollaborationUse theUse) {
            return new GmCollabUseLink(this.diagram, theUse, new MRef(theUse));
        }

        @objid ("f43c0819-ff4c-4900-8ff7-27a550294ca1")
        @Override
        public Object visitComponentRealization(ComponentRealization obj) {
            return new GmComponentRealization(this.diagram, obj, new MRef(obj));
        }

        @objid ("4a647ddd-07e2-4478-81f0-e6f206191d0e")
        @Override
        public Object visitConnector(final Connector connector) {
            // If lollipop Connector is not a link
            if (connector.getLinkEnd().get(0).getConsumer() != null || connector.getLinkEnd().get(0).getProvider() != null || connector.getLinkEnd().get(0).getOpposite().getConsumer() != null
                    || connector.getLinkEnd().get(0).getOpposite().getProvider() != null) {
                return null;
            }
            return new GmConnectorLink(this.diagram,
                                (ConnectorEnd) connector.getLinkEnd().get(0),
                                new MRef(connector.getLinkEnd().get(0)),
                                new MRef(connector));
        }

        @objid ("aa05fcba-1a31-4803-8cf0-c85d69e74927")
        @Override
        public Object visitConnectorEnd(final ConnectorEnd role) {
            // If lollipop Connector is not a link
            if (role.getConsumer() != null || role.getProvider() != null || role.getOpposite().getConsumer() != null || role.getOpposite().getProvider() != null) {
                return null;
            }
            return new GmConnectorLink(this.diagram,
                                role,
                                new MRef(role),
                                new MRef(role.getLink()));
        }

        @objid ("36ba9fec-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitElementImport(ElementImport theElementImport) {
            return new GmElementImport(this.diagram, theElementImport, new MRef(theElementImport));
        }

        @objid ("93e9231d-20af-4539-85a8-b13e1b9f8b16")
        @Override
        public Object visitElementRealization(ElementRealization obj) {
            return new GmElementRealization(this.diagram, obj, new MRef(obj));
        }

        @objid ("36ba9ff4-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitGeneralization(Generalization theGeneralization) {
            return new GmGeneralization(this.diagram, theGeneralization, new MRef(theGeneralization));
        }

        @objid ("36bc267c-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitInformationFlow(final InformationFlow theFlow) {
            if (theFlow.getInformationSource().size() == 1 && theFlow.getInformationTarget().size() == 1) {
                return new GmInformationFlowLink(this.diagram, theFlow, new MRef(theFlow));
            }
            return super.visitInformationFlow(theFlow);
        }

        @objid ("36bc2697-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitInterfaceRealization(final InterfaceRealization theInterfaceRealization) {
            return new GmInterfaceRealization(this.diagram,
                                theInterfaceRealization,
                                new MRef(theInterfaceRealization));
        }

        @objid ("ed32b086-fb63-4501-831a-14e836312cb1")
        @Override
        public Object visitLink(Link link) {
            return new GmInstanceLink(this.diagram,
                                link.getLinkEnd().get(0),
                                new MRef(link.getLinkEnd().get(0)),
                                new MRef(link));
        }

        @objid ("78977fe7-b15c-4423-bfbb-d970d2710161")
        @Override
        public Object visitLinkEnd(LinkEnd role) {
            return new GmInstanceLink(this.diagram,
                                role,
                                new MRef(role),
                                new MRef(role.getLink()));
        }

        @objid ("75ba39e1-96c6-4eae-8501-2ccd74fe6f84")
        @Override
        public Object visitNaryAssociationEnd(NaryAssociationEnd role) {
            return new GmNAssocEndLink(this.diagram, role, new MRef(role));
        }

        @objid ("f4e730b6-3faf-41da-b954-cc37980d2059")
        @Override
        public Object visitNaryConnectorEnd(final NaryConnectorEnd role) {
            return new GmNConnectorEndLink(this.diagram, role, new MRef(role));
        }

        @objid ("42f8487f-c36f-4bd4-b0c2-f88be0b7ee12")
        @Override
        public Object visitNaryLinkEnd(NaryLinkEnd role) {
            return new GmNLinkEndLink(this.diagram, role, new MRef(role));
        }

        @objid ("36bc2661-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitPackageImport(final PackageImport thePackageImport) {
            return new GmPackageImport(this.diagram, thePackageImport, new MRef(thePackageImport));
        }

        @objid ("36bc266a-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitPackageMerge(final PackageMerge thePackageMerge) {
            return new GmPackageMerge(this.diagram, thePackageMerge, new MRef(thePackageMerge));
        }

        @objid ("36bc2685-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitProvidedInterface(final ProvidedInterface theProvidedInterface) {
            return new GmProvidedInterfaceLink(this.diagram,
                                theProvidedInterface,
                                new MRef(theProvidedInterface));
        }

        @objid ("36bc2673-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitRaisedException(final RaisedException theRaisedException) {
            return new GmRaisedException(this.diagram, theRaisedException, new MRef(theRaisedException));
        }

        @objid ("36bc268e-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitRequiredInterface(final RequiredInterface theRequiredInterface) {
            return new GmRequiredInterfaceLink(this.diagram,
                                theRequiredInterface,
                                new MRef(theRequiredInterface));
        }

        @objid ("2c47fdb8-ab78-4103-bc2c-0d21a42b9819")
        @Override
        public Object visitSubstitution(Substitution obj) {
            return new GmSubstitution(this.diagram, obj, new MRef(obj));
        }

        @objid ("36bdacfe-55b7-11e2-877f-002564c97630")
        @Override
        public Object visitTemplateBinding(final TemplateBinding theTemplateBinding) {
            return new GmTemplateBinding(this.diagram, theTemplateBinding, new MRef(theTemplateBinding));
        }

        @objid ("8d7c9dd2-58d6-42b8-b15e-f4a57e1ea836")
        @Override
        public Object visitUsage(Usage theUsage) {
            return new GmUsage(this.diagram, theUsage, new MRef(theUsage));
        }

    }

}
