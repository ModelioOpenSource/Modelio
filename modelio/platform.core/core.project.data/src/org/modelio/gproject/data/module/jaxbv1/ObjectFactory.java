/* 
 * Copyright 2013-2018 Modeliosoft
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

package org.modelio.gproject.data.module.jaxbv1;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.modeliosoft.modelio.mdainfra.mdacs.core.configuration.model package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@objid ("caee75b6-d6ff-11e1-9f03-001ec947ccaf")
@XmlRegistry
@SuppressWarnings("static-method")
public class ObjectFactory {
    @objid ("cae9b1a0-d6ff-11e1-9f03-001ec947ccaf")
    private static final QName _DiagramCommandLinkScopeSource_QNAME = new QName("", "scope-source");

    @objid ("cae9b1a1-d6ff-11e1-9f03-001ec947ccaf")
    private static final QName _DiagramCommandLinkScopeTarget_QNAME = new QName("", "scope-target");

    @objid ("cae9b1a2-d6ff-11e1-9f03-001ec947ccaf")
    private static final QName _DiagramCommandLinkHandler_QNAME = new QName("", "handler");

    @objid ("cae9b1a3-d6ff-11e1-9f03-001ec947ccaf")
    private static final QName _ModuleGuiCustomizedDiagramPaletteDiagramCommandAttachedbox_QNAME = new QName("", "diagram-command-attachedbox");

    @objid ("cae9b1a4-d6ff-11e1-9f03-001ec947ccaf")
    private static final QName _ModuleGuiCustomizedDiagramPaletteDiagramCommand_QNAME = new QName("", "diagram-command");

    @objid ("cae9b1a5-d6ff-11e1-9f03-001ec947ccaf")
    private static final QName _ModuleGuiCustomizedDiagramPaletteDiagramCommandBox_QNAME = new QName("", "diagram-command-box");

    @objid ("cae9b1a6-d6ff-11e1-9f03-001ec947ccaf")
    private static final QName _ModuleGuiCustomizedDiagramPaletteDiagramCommandLink_QNAME = new QName("", "diagram-command-link");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.modeliosoft.modelio.mdainfra.mdacs.core.configuration.model
     */
    @objid ("cae74ea1-d6ff-11e1-9f03-001ec947ccaf")
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JxbModule.Gui.CustomizedDiagram }
     */
    @objid ("cae74ea2-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.CustomizedDiagram createModuleGuiCustomizedDiagram() {
        return new JxbModule.Gui.CustomizedDiagram();
    }

    /**
     * Create an instance of {@link JxbModule.JxbProfile.JxbStereotype.Icons.Small }
     */
    @objid ("cae74ea3-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile.JxbStereotype.Icons.Small createModuleProfileStereotypeIconsSmall() {
        return new JxbModule.JxbProfile.JxbStereotype.Icons.Small();
    }

    /**
     * Create an instance of {@link JxbModule.JxbProfile.JxbStereotype.Icons }
     */
    @objid ("cae74ea4-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile.JxbStereotype.Icons createModuleProfileStereotypeIcons() {
        return new JxbModule.JxbProfile.JxbStereotype.Icons();
    }

    /**
     * Create an instance of {@link JxbModule.JxbProfile.JxbStereotype.Icons.Diagram }
     */
    @objid ("cae74ea5-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile.JxbStereotype.Icons.Diagram createModuleProfileStereotypeIconsDiagram() {
        return new JxbModule.JxbProfile.JxbStereotype.Icons.Diagram();
    }

    /**
     * Create an instance of {@link JxbModule.Gui.PropertyPage }
     */
    @objid ("cae9b181-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.PropertyPage createModuleGuiPropertyPage() {
        return new JxbModule.Gui.PropertyPage();
    }

    /**
     * Create an instance of {@link JxbModule.Dependencies.Required }
     */
    @objid ("cae9b182-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.Dependencies.Required createModuleDependenciesRequired() {
        return new JxbModule.Dependencies.Required();
    }

    /**
     * Create an instance of {@link JxbModule.Gui.CustomizedDiagram.Palette }
     */
    @objid ("cae9b183-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.CustomizedDiagram.Palette createModuleGuiCustomizedDiagramPalette() {
        return new JxbModule.Gui.CustomizedDiagram.Palette();
    }

    /**
     * Create an instance of {@link JxbModule.JxbParameter }
     */
    @objid ("cae9b184-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbParameter createModuleParameter() {
        return new JxbModule.JxbParameter();
    }

    /**
     * Create an instance of {@link JxbDiagramCommand }
     */
    @objid ("cae9b185-d6ff-11e1-9f03-001ec947ccaf")
    public JxbDiagramCommand createDiagramCommand() {
        return new JxbDiagramCommand();
    }

    /**
     * Create an instance of {@link JxbModule.JxbProfile.JxbStereotype }
     */
    @objid ("cae9b186-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile.JxbStereotype createModuleProfileStereotype() {
        return new JxbModule.JxbProfile.JxbStereotype();
    }

    /**
     * Create an instance of {@link JxbHandler }
     */
    @objid ("cae9b187-d6ff-11e1-9f03-001ec947ccaf")
    public JxbHandler createHandler() {
        return new JxbHandler();
    }

    /**
     * Create an instance of {@link JxbScope }
     */
    @objid ("cae9b188-d6ff-11e1-9f03-001ec947ccaf")
    public JxbScope createScope() {
        return new JxbScope();
    }

    /**
     * Create an instance of {@link JxbModule.JxbProfile.JxbStereotype.Icons.Explorer }
     */
    @objid ("cae9b189-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile.JxbStereotype.Icons.Explorer createModuleProfileStereotypeIconsExplorer() {
        return new JxbModule.JxbProfile.JxbStereotype.Icons.Explorer();
    }

    /**
     * Create an instance of {@link JxbModule.Gui.Command }
     */
    @objid ("cae9b18a-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.Command createModuleGuiCommand() {
        return new JxbModule.Gui.Command();
    }

    /**
     * Create an instance of {@link JxbModule.JxbParameter.JxbEnumeration }
     */
    @objid ("cae9b18b-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbParameter.JxbEnumeration createModuleParameterEnumeration() {
        return new JxbModule.JxbParameter.JxbEnumeration();
    }

    /**
     * Create an instance of {@link JxbNotetype }
     */
    @objid ("cae9b18c-d6ff-11e1-9f03-001ec947ccaf")
    public JxbNotetype createNotetype() {
        return new JxbNotetype();
    }

    /**
     * Create an instance of {@link JxbClasspath.Entry }
     */
    @objid ("cae9b18d-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbClasspath.Entry createClasspathEntry() {
        return new JxbClasspath.Entry();
    }

    /**
     * Create an instance of {@link JxbModule.Gui.CustomizedDiagram.Style.StyleProperty }
     */
    @objid ("cae9b18e-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.CustomizedDiagram.Style.StyleProperty createModuleGuiCustomizedDiagramStyleStyleProperty() {
        return new JxbModule.Gui.CustomizedDiagram.Style.StyleProperty();
    }

    /**
     * Create an instance of {@link JxbModule.Dependencies.Optional }
     */
    @objid ("cae9b18f-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.Dependencies.Optional createModuleDependenciesOptional() {
        return new JxbModule.Dependencies.Optional();
    }

    /**
     * Create an instance of {@link JxbModule.Dependencies }
     */
    @objid ("cae9b190-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.Dependencies createModuleDependencies() {
        return new JxbModule.Dependencies();
    }

    /**
     * Create an instance of {@link JxbModule.JxbParameter.JxbEnumeration.Literal }
     */
    @objid ("cae9b191-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbParameter.JxbEnumeration.Literal createModuleParameterEnumerationLiteral() {
        return new JxbModule.JxbParameter.JxbEnumeration.Literal();
    }

    /**
     * Create an instance of {@link JxbTaggedvalues }
     */
    @objid ("cae9b192-d6ff-11e1-9f03-001ec947ccaf")
    public JxbTaggedvalues createTaggedvalues() {
        return new JxbTaggedvalues();
    }

    /**
     * Create an instance of {@link JxbDiagramCommandLink }
     */
    @objid ("cae9b193-d6ff-11e1-9f03-001ec947ccaf")
    public JxbDiagramCommandLink createDiagramCommandLink() {
        return new JxbDiagramCommandLink();
    }

    /**
     * Create an instance of {@link JxbClasspath }
     */
    @objid ("cae9b194-d6ff-11e1-9f03-001ec947ccaf")
    public JxbClasspath createClasspath() {
        return new JxbClasspath();
    }

    /**
     * Create an instance of {@link JxbContextualCommand }
     */
    @objid ("cae9b195-d6ff-11e1-9f03-001ec947ccaf")
    public JxbContextualCommand createContextualCommand() {
        return new JxbContextualCommand();
    }

    /**
     * Create an instance of {@link JxbModule }
     */
    @objid ("cae9b196-d6ff-11e1-9f03-001ec947ccaf")
    public JxbModule createModule() {
        return new JxbModule();
    }

    /**
     * Create an instance of {@link JxbModule.Gui.ElementCreationCommand }
     */
    @objid ("cae9b197-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.ElementCreationCommand createModuleGuiElementCreationCommand() {
        return new JxbModule.Gui.ElementCreationCommand();
    }

    /**
     * Create an instance of {@link JxbModule.Dependencies.Ramc }
     */
    @objid ("cae9b198-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.Dependencies.Ramc createModuleDependenciesRamc() {
        return new JxbModule.Dependencies.Ramc();
    }

    /**
     * Create an instance of {@link JxbModule.JxbProfile.JxbAnonymousStereotype }
     */
    @objid ("cae9b199-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile.JxbAnonymousStereotype createModuleProfileAnonymousStereotype() {
        return new JxbModule.JxbProfile.JxbAnonymousStereotype();
    }

    /**
     * Create an instance of {@link JxbContextualCommand.Contribution }
     */
    @objid ("cae9b19a-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbContextualCommand.Contribution createContextualCommandContribution() {
        return new JxbContextualCommand.Contribution();
    }

    /**
     * Create an instance of {@link JxbModule.JxbProfile }
     */
    @objid ("cae9b19b-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.JxbProfile createModuleProfile() {
        return new JxbModule.JxbProfile();
    }

    /**
     * Create an instance of {@link JxbModule.Gui }
     */
    @objid ("cae9b19c-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui createModuleGui() {
        return new JxbModule.Gui();
    }

    /**
     * Create an instance of {@link JxbDiagramCommandBox }
     */
    @objid ("cae9b19d-d6ff-11e1-9f03-001ec947ccaf")
    public JxbDiagramCommandBox createDiagramCommandBox() {
        return new JxbDiagramCommandBox();
    }

    /**
     * Create an instance of {@link JxbModule.Gui.CustomizedDiagram.Style }
     */
    @objid ("cae9b19e-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbModule.Gui.CustomizedDiagram.Style createModuleGuiCustomizedDiagramStyle() {
        return new JxbModule.Gui.CustomizedDiagram.Style();
    }

    /**
     * Create an instance of {@link JxbExterndocumenttype }
     */
    @objid ("cae9b19f-d6ff-11e1-9f03-001ec947ccaf")
    public JxbExterndocumenttype createExterndocumenttype() {
        return new JxbExterndocumenttype();
    }

    /**
     * Create an instance of {@link JxbDocpath }
     */
    @objid ("cae9b1a7-d6ff-11e1-9f03-001ec947ccaf")
    public JxbDocpath createDocpath() {
        return new JxbDocpath();
    }

    /**
     * Create an instance of {@link JxbDocpath.Entry }
     */
    @objid ("cae9b1a8-d6ff-11e1-9f03-001ec947ccaf")
    public org.modelio.gproject.data.module.jaxbv1.JxbDocpath.Entry createDocpathEntry() {
        return new JxbDocpath.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JxbScope }{@code >}}
     */
    @objid ("cae9b1a9-d6ff-11e1-9f03-001ec947ccaf")
    @XmlElementDecl(namespace = "", name = "scope-source", scope = JxbDiagramCommandLink.class)
    public JAXBElement<JxbScope> createDiagramCommandLinkScopeSource(final JxbScope value) {
        return new JAXBElement<>(_DiagramCommandLinkScopeSource_QNAME, JxbScope.class, JxbDiagramCommandLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JxbScope }{@code >}}
     */
    @objid ("cae9b1aa-d6ff-11e1-9f03-001ec947ccaf")
    @XmlElementDecl(namespace = "", name = "scope-target", scope = JxbDiagramCommandLink.class)
    public JAXBElement<JxbScope> createDiagramCommandLinkScopeTarget(final JxbScope value) {
        return new JAXBElement<>(_DiagramCommandLinkScopeTarget_QNAME, JxbScope.class, JxbDiagramCommandLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JxbHandler }{@code >}}
     */
    @objid ("cae74f46-d6ff-11e1-9f03-001ec947ccaf")
    @XmlElementDecl(namespace = "", name = "handler", scope = JxbDiagramCommandLink.class)
    public JAXBElement<JxbHandler> createDiagramCommandLinkHandler(final JxbHandler value) {
        return new JAXBElement<>(_DiagramCommandLinkHandler_QNAME, JxbHandler.class, JxbDiagramCommandLink.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JxbDiagramCommandBox }{@code >}}
     */
    @objid ("cae74f47-d6ff-11e1-9f03-001ec947ccaf")
    @XmlElementDecl(namespace = "", name = "diagram-command-attachedbox", scope = JxbModule.Gui.CustomizedDiagram.Palette.class)
    public JAXBElement<JxbDiagramCommandBox> createModuleGuiCustomizedDiagramPaletteDiagramCommandAttachedbox(final JxbDiagramCommandBox value) {
        return new JAXBElement<>(_ModuleGuiCustomizedDiagramPaletteDiagramCommandAttachedbox_QNAME, JxbDiagramCommandBox.class, JxbModule.Gui.CustomizedDiagram.Palette.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JxbDiagramCommand }{@code >}}
     */
    @objid ("cae74f48-d6ff-11e1-9f03-001ec947ccaf")
    @XmlElementDecl(namespace = "", name = "diagram-command", scope = JxbModule.Gui.CustomizedDiagram.Palette.class)
    public JAXBElement<JxbDiagramCommand> createModuleGuiCustomizedDiagramPaletteDiagramCommand(final JxbDiagramCommand value) {
        return new JAXBElement<>(_ModuleGuiCustomizedDiagramPaletteDiagramCommand_QNAME, JxbDiagramCommand.class, JxbModule.Gui.CustomizedDiagram.Palette.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JxbDiagramCommandBox }{@code >}}
     */
    @objid ("cae74f49-d6ff-11e1-9f03-001ec947ccaf")
    @XmlElementDecl(namespace = "", name = "diagram-command-box", scope = JxbModule.Gui.CustomizedDiagram.Palette.class)
    public JAXBElement<JxbDiagramCommandBox> createModuleGuiCustomizedDiagramPaletteDiagramCommandBox(final JxbDiagramCommandBox value) {
        return new JAXBElement<>(_ModuleGuiCustomizedDiagramPaletteDiagramCommandBox_QNAME, JxbDiagramCommandBox.class, JxbModule.Gui.CustomizedDiagram.Palette.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JxbDiagramCommandLink }{@code >}}
     */
    @objid ("cae74f4a-d6ff-11e1-9f03-001ec947ccaf")
    @XmlElementDecl(namespace = "", name = "diagram-command-link", scope = JxbModule.Gui.CustomizedDiagram.Palette.class)
    public JAXBElement<JxbDiagramCommandLink> createModuleGuiCustomizedDiagramPaletteDiagramCommandLink(final JxbDiagramCommandLink value) {
        return new JAXBElement<>(_ModuleGuiCustomizedDiagramPaletteDiagramCommandLink_QNAME, JxbDiagramCommandLink.class, JxbModule.Gui.CustomizedDiagram.Palette.class, value);
    }

}
