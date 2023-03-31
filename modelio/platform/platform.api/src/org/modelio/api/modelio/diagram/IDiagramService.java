/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package org.modelio.api.modelio.diagram;

import java.io.File;
import java.util.List;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.eclipse.gef.palette.PaletteEntry;
import org.modelio.api.modelio.diagram.style.IStyleHandle;
import org.modelio.api.modelio.diagram.tools.IAttachedBoxTool;
import org.modelio.api.modelio.diagram.tools.IBoxTool;
import org.modelio.api.modelio.diagram.tools.ILinkTool;
import org.modelio.api.modelio.diagram.tools.IMultiLinkTool;
import org.modelio.api.modelio.editor.IEditionService;
import org.modelio.api.module.contributor.diagramcreation.IDiagramWizardContributor;
import org.modelio.api.ui.viewtemplate.IModelViewTemplate;
import org.modelio.metamodel.diagrams.AbstractDiagram;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.Component;
import org.modelio.metamodel.uml.statik.NameSpace;
import org.modelio.metamodel.uml.statik.Package;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * This interface gives an entry point to manipulate the Modelio diagrams.<br>
 * It also manages diagram customization and diagram styles.
 */
@objid ("e05b1536-6bf4-11e0-a371-001ec947cd2a")
public interface IDiagramService {
    /**
     * Tool that creates drawing lines.
     * 
     * @since 3.6
     */
    @objid ("053da8a5-790d-4b15-88ae-3e05130d5ef0")
    public static final String TOOL_CREATE_DRAWING_LINE = "CREATE_DRAWING_LINE";

    /**
     * Tool that creates drawing texts.
     * 
     * @since 3.6
     */
    @objid ("76097161-4a00-4b98-9cc9-7a485ef74989")
    public static final String TOOL_CREATE_DRAWING_TEXT = "CREATE_DRAWING_TEXT";

    /**
     * Tool that creates drawing ellipses.
     * 
     * @since 3.6
     */
    @objid ("5a9c2850-dd84-4bf3-a451-be92eb67f3b2")
    public static final String TOOL_CREATE_DRAWING_ELLIPSE = "CREATE_DRAWING_ELLIPSE";

    /**
     * Tool that creates drawing rectangles.
     * 
     * @since 3.6
     */
    @objid ("a6133f7f-bdfe-4b61-b9e4-12cd074f50e5")
    public static final String TOOL_CREATE_DRAWING_RECTANGLE = "CREATE_DRAWING_RECTANGLE";

    /**
     * ID of the link creation tool that shows popup menu to choose the kind of link.
     * 
     * @since 3.6
     */
    @objid ("0aca3bed-52b5-42da-affb-5d8bd1214d99")
    public static final String TOOL_POPUPMENU_CREATELINK = "POPUPMENU_CREATELINK_TOOL";

    /**
     * Get an installed diagram styles from its name.
     * @since 2.2
     * @param name the name of the style to look for.
     * @return The style with the given name, or <code>null</code> if it isn't installed.
     */
    @objid ("ff24bfde-7e65-11e1-b95c-002564c97630")
    IStyleHandle getStyle(final String name);

    /**
     * List all diagram styles that are currently installed.
     * @return A list of styles. Might be empty, but not <code>null</code>.
     */
    @objid ("7ed90eca-d7a5-11e0-9245-001ec947cd2a")
    List<IStyleHandle> listStyles();

    /**
     * Register a new named style along with its 'data' file.<br>
     * @param styleName then name of the style to register.
     * @param baseStyleName the cascaded style.
     * @param styleData the list of all properties defined in the style.
     * @return then newly created IStyleHandle or <code>null</code> if the style could not be created. When the style already exists it is simply returned.
     */
    @objid ("7ed90ec3-d7a5-11e0-9245-001ec947cd2a")
    IStyleHandle registerStyle(final String styleName, final String baseStyleName, final File styleData);

    /**
     * Get a diagram handle on 'diagram'.<br>
     * It opens a new diagram editor in Modelio, or select it if already opened.
     * @param diagram the AbstractDiagram to open.
     * @return a diagram handle representing this diagram.
     */
    @objid ("a3e9293d-0ecc-11e2-96c4-002564c97630")
    IDiagramHandle getDiagramHandle(final AbstractDiagram diagram);

    /**
     * Get the palette tool from the given id.
     * @param id An id.
     * @return the found palette tool or <i>null</i> if none found.
     */
    @objid ("a3e9504f-0ecc-11e2-96c4-002564c97630")
    PaletteEntry getRegisteredTool(final String id);

    @objid ("18c7be9c-b77a-48b5-a511-141901dd0505")
    void registerCustomizedTool(final String id, MClass metaclass, final Stereotype stereotype, final String dependency, final IBoxTool handler);

    @objid ("05e80879-c80a-4f2d-9639-944ba48ebbb7")
    void registerCustomizedTool(final String id, MClass metaclass, final Stereotype stereotype, final String dependency, final IAttachedBoxTool handler);

    @objid ("7a79e9af-8dcc-4270-8610-e4772d06b691")
    void registerCustomizedTool(final String id, MClass metaclass, final Stereotype stereotype, final String dependency, final ILinkTool handler);

    @objid ("9b03c21d-8ec5-4808-8a4f-df434b644246")
    void registerCustomizedTool(final String id, MClass metaclass, final Stereotype stereotype, final String dependency, final IMultiLinkTool handler);

    @objid ("e90872ae-6052-46a6-9557-0a94d5ab1914")
    void registerDiagramCustomization(final Stereotype stereotype, MClass baseDiagramClass, final IDiagramCustomizer customizer);

    @objid ("63ae1f44-0c8e-4c9b-b67d-27d875d82cdc")
    void unregisterDiagramCustomization(final Stereotype stereotype, MClass baseDiagramClass, final IDiagramCustomizer customizer);

    @objid ("78f2438e-ba22-44fa-a765-88b4787638ae")
    void unregisterCustomizedTool(String id);

    /**
     * Register diagram contributor
     * @param category of the diagram contributor
     * @deprecated use {@link IEditionService#registerDiagramContributor}
     * @param contributor the diagram contributor
     */
    @objid ("bda5f375-7af3-49b4-8d3f-54537aff2a13")
    @Deprecated
    void registerDiagramContributor(ContributorCategory category, IDiagramWizardContributor contributor);

    /**
     * Unregister diagram contributor
     * @param category of the diagram contributor
     * @deprecated use {@link IEditionService#unregisterDiagramContributor}
     * @param contributor the diagram contributor
     */
    @objid ("ec9cb413-7f27-4591-8222-d7ab2e780484")
    @Deprecated
    void unregisterDiagramContributor(ContributorCategory category, IDiagramWizardContributor contributor);

    /**
     * Get a Diagram Creator from its id. Supported values are:
     * <table border='1'>
     * <tr>
     * <th>Creator identifier</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>"ClassArchitectureDiagramTemplate"</td>
     * <td>A Class Architecture diagram shows how a class is made, what it consists of, what it is in terms of inheritance and its place in the project.
     * </p>
     * <p>
     * For a class, it includes:
     * <ul>
     * <li>The class itself in the center.</li>
     * <li>Its public attributes.</li>
     * <li>The parent classes/interfaces if any.</li>
     * <li>The child classes/interfaces if any.</li>
     * <li>The classes it is having an association to.</li>
     * <li>The classes having any kind of association towards it.</li>
     * </ul>
     * </td>
     * </tr>
     * <tr>
     * <td>"ClassStructureDiagramTemplate"</td>
     * <td>A Class Structure diagram shows how a class is made, what it consists of, what it is in terms of inheritance.
     * </p>
     * <p>
     * For a class, it includes:
     * <ul>
     * <li>The class itself in the center.</li>
     * <li>Its public attributes.</li>
     * <li>The parent classes/interfaces if any.</li>
     * <li>The classes it is having an association to.</li>
     * <li>The classes having a composition or aggregation towards it.</li>
     * </ul>
     * </td>
     * </tr>
     * <tr>
     * <td>"CompositionNavigationDiagramTemplate"</td>
     * <td>A Composition diagram shows owned elements of a ModelTree.
     * <p>
     * For a {@link ModelTree}, it includes:
     * <ul>
     * <li>The owned ModelTrees layouted on a grid.</li>
     * </ul>
     * </p>
     * </td>
     * </tr>
     * <tr>
     * <td>"DependencyDiagramTemplate"</td>
     * <td>A Dependency diagram shows incoming and outgoing dependencies of a {@link NameSpace}.
     * </p>
     * <p>
     * For a {@link NameSpace}, it includes:
     * <ul>
     * <li>The {@link NameSpace} itself in a central position.
     * <li>All elements having a dependency towards the {@link NameSpace} on the left.</li>
     * <li>All elements having the {@link NameSpace} depends on on the right.</li>
     * </ul>
     * </td>
     * </tr>
     * <tr>
     * <td>"InheritanceDiagramTemplate"</td>
     * <td>A Class Inheritance diagram shows where a {@link Classifier} is in its inheritance graph.
     * </p>
     * <p>
     * For a {@link Classifier}, it includes:
     * <ul>
     * <li>The {@link Classifier} itself in the center.</li>
     * <li>The parent classes/interfaces if any.</li>
     * <li>The parent sub-classes if any.</li>
     * </ul>
     * </td>
     * </tr>
     * <tr>
     * <td>"PackageContentStructureDiagramTemplate"</td>
     * <td>A SubPackage Structure diagram shows the internal structure of a package, and the links between them.
     * </p>
     * <p>
     * For a package, it includes:
     * <ul>
     * <li>Its sub-classes/interfaces.</li>
     * <li>The associations and inheritance relationships between them.</li>
     * </ul>
     * </td>
     * </tr>
     * <tr>
     * <td>"SubPackageStructureDiagramTemplate"</td>
     * <td>A SubPackage Structure diagram shows the internal structure of a {@link Package} or {@link Component} in terms of packages, and the dependencies between them.
     * </p>
     * <p>
     * It includes:
     * <ul>
     * <li>Its sub-packages.</li>
     * <li>The blue links (impact links) between those sub-packages.</li>
     * </ul>
     * </td>
     * </tr>
     * <tr>
     * <td>"UseCaseFocusDiagramTemplate"</td>
     * <td>A 'Use Case close up' diagram shows for a {@link UseCase}:
     * <ul>
     * <li>The use case itself.</li>
     * <li>The actors associated to the use case</li>
     * <li>The associations between actors and usecase</li>
     * </ul>
     * </td>
     * </tr>
     * </table>
     * @param modelViewTemplateId identifier of the diagram template.
     * @since 4.1
     * @return a new Diagram Creator. Might be <code>null</code> if the id is not found.
     */
    @objid ("492c6dc7-808d-4dac-9d63-965b1afe180e")
    IModelViewTemplate<AbstractDiagram> getDiagramTemplate(String modelViewTemplateId);
}

