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

package org.modelio.api.modelio.diagram.autodiagram;

import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.behavior.usecaseModel.UseCase;
import org.modelio.metamodel.uml.infrastructure.ModelTree;
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.metamodel.uml.statik.NameSpace;

/**
 * The IAutoDiagramFactory provides the ability to create automatic diagram managers.
 * <p>
 * Only one automatic diagram of each type can be created for an element.
 * </p>
 * <p>
 * Automatic diagrams are meant to help analyze a design for maintenance, improvements or refactoring. Two main categories exist:
 * <ul>
 * <li>Structure Diagrams, analyzing what an element contains, what it is.</li>
 * <li>Position Diagrams, analyzing what role an element has in a system, where it is.</li>
 * </ul>
 * </p>
 * 
 * @see IDiagramCreator
 * @since 2.2
 */
@objid ("ceb681b1-7e65-11e1-b95c-002564c97630")
public interface IAutoDiagramFactory {
    /**
     * Instantiates a new Diagram Creator for Class Structure diagrams.
     * <p>
     * A Class Structure diagram shows how a class is made, what it consists of, what it is in terms of inheritance.
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
     * </p>
     * 
     * @return a new Diagram Creator.
     * @deprecated use <code>createDiagramCreator("ClassStructureCreator")</code> instead.
     */
    @objid ("ceb681b2-7e65-11e1-b95c-002564c97630")
    @Deprecated
    IDiagramCreator createClassStructureCreator();

    /**
     * Instantiates a new Diagram Creator for Inheritance diagrams.
     * <p>
     * A Class Inheritance diagram shows where a class is in its inheritance graph.
     * </p>
     * <p>
     * For a class, it includes:
     * <ul>
     * <li>The class itself in the center.</li>
     * <li>The parent classes/interfaces if any.</li>
     * <li>The parent sub-classes if any.</li>
     * </ul>
     * </p>
     * 
     * @return a new Diagram Creator.
     * @deprecated use <code>createDiagramCreator("InheritanceCreator")</code> instead.
     */
    @objid ("ceb6a8c3-7e65-11e1-b95c-002564c97630")
    @Deprecated
    IDiagramCreator createInheritanceCreator();

    /**
     * Instantiates a new Diagram Creator for SubPackage Structure diagrams.
     * <p>
     * A SubPackage Structure diagram shows the internal structure of a package in terms of packages, and the dependencies between them.
     * </p>
     * <p>
     * For a package, it includes:
     * <ul>
     * <li>Its sub-packages.</li>
     * <li>The blue links (impact links) between those sub-packages.</li>
     * </ul>
     * </p>
     * 
     * @return a new Diagram Creator.
     * @deprecated use <code>createDiagramCreator("SubPackageStructureCreator")</code> instead.
     */
    @objid ("ceb6a8c5-7e65-11e1-b95c-002564c97630")
    @Deprecated
    IDiagramCreator createSubPackageStructureCreator();

    /**
     * Instantiates a new Diagram Creator for Package Content Structure diagrams.
     * <p>
     * A SubPackage Structure diagram shows the internal structure of a package, and the links between them.
     * </p>
     * <p>
     * For a package, it includes:
     * <ul>
     * <li>Its sub-classes/interfaces.</li>
     * <li>The associations and inheritance relationships between them.</li>
     * </ul>
     * </p>
     * 
     * @return a new Diagram Creator.
     * @deprecated use <code>createDiagramCreator("PackageContentStructureCreator")</code> instead.
     */
    @objid ("ceb6a8c7-7e65-11e1-b95c-002564c97630")
    @Deprecated
    IDiagramCreator createPackageContentStructureCreator();

    /**
     * Instantiates a new Diagram Creator for Dependency diagrams.
     * <p>
     * A Dependency diagram shows incoming and outgoing dependencies of a namespace.
     * </p>
     * <p>
     * For a namespace, it includes:
     * <ul>
     * <li>The namespace itself in a central position.
     * <li>All elements having a dependency towards the namespace on the left.</li>
     * <li>All elements having the namespace depends on on the right.</li>
     * </ul>
     * </p>
     * 
     * @return a new Diagram Creator.
     * @deprecated use <code>createDiagramCreator("DependencyCreator")</code> instead.
     */
    @objid ("ceb6a8c9-7e65-11e1-b95c-002564c97630")
    @Deprecated
    IDiagramCreator createDependencyCreator();

    /**
     * Instantiates a new Diagram Creator for Class Architecture diagrams.
     * <p>
     * A Class Architecture diagram shows how a class is made, what it consists of, what it is in terms of inheritance and its place in the project.
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
     * </p>
     * 
     * @return a new Diagram Creator.
     * @deprecated use <code>createDiagramCreator("ClassArchitectureCreator")</code> instead.
     */
    @objid ("e542ceff-7064-4206-8f6b-ec176947591d")
    @Deprecated
    IDiagramCreator createClassArchitectureCreator();

    /**
     * Instantiates a new Diagram Creator for Composition diagrams.
     * <p>
     * For a {@link ModelTree}, it includes:
     * <ul>
     * <li>The owned ModelTrees layouted on a grid.</li>
     * </ul>
     * </p>
     * 
     * @return a new Diagram Creator.
     * @deprecated use <code>createDiagramCreator("CompositionNavigationCreator")</code> instead.
     */
    @objid ("24528d8f-2159-47b7-9eea-afc985865df9")
    @Deprecated
    IDiagramCreator createCompositionNavigationCreator();

    /**
     * Instantiates a new Diagram Creator for Use Cases.
     * <p>
     * A 'Use Case close up' diagram shows for a {@link UseCase}:
     * <ul>
     * <li>The use case itself.</li>
     * <li>The actors associated to the use case</li>
     * <li>The associations between actors and usecase</li>
     * </ul>
     * </p>
     * 
     * @return a new Diagram Creator.
     * @since 4.0
     * @deprecated use <code>createDiagramCreator("UseCaseFocusCreator")</code> instead.
     */
    @objid ("3a29eb86-62cf-43f1-80e2-e68beee92925")
    @Deprecated
    IDiagramCreator createUseCaseFocusCreator();

    /**
     * Instantiates a new Diagram Creator from its id. Supported values are:
     * <table border='1'>
     * <tr>
     * <th>Creator identifier</th>
     * <th>Description</th>
     * </tr>
     * <tr>
     * <td>"ClassArchitectureCreator"</td>
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
     * <td>"ClassStructureCreator"</td>
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
     * <td>"CompositionNavigationCreator"</td>
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
     * <td>"DependencyCreator"</td>
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
     * <td>"InheritanceCreator"</td>
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
     * <td>"PackageContentStructureCreator"</td>
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
     * <td>"SubPackageStructureCreator"</td>
     * <td>A SubPackage Structure diagram shows the internal structure of a package in terms of packages, and the dependencies between them.
     * </p>
     * <p>
     * For a package, it includes:
     * <ul>
     * <li>Its sub-packages.</li>
     * <li>The blue links (impact links) between those sub-packages.</li>
     * </ul>
     * </td>
     * </tr>
     * <tr>
     * <td>"UseCaseFocusCreator"</td>
     * <td>A 'Use Case close up' diagram shows for a {@link UseCase}:
     * <ul>
     * <li>The use case itself.</li>
     * <li>The actors associated to the use case</li>
     * <li>The associations between actors and usecase</li>
     * </ul>
     * </td>
     * </tr>
     * </table>
     * 
     * @param creatorId identifier of the diagram creator.
     * @since 4.0
     * @return a new Diagram Creator. Might be <code>null</code> if the id is not found.
     */
    @objid ("aea7d639-3d87-45f5-8376-90c319635913")
    IDiagramCreator createDiagramCreator(String creatorId);

}
