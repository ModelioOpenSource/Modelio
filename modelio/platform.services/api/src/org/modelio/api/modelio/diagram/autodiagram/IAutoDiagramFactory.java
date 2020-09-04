/* 
 * Copyright 2013-2018 Modeliosoft
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

/**
 * The IAutoDiagramFactory provides the ability to create automatic diagram managers.
 * <p>
 * Only one automatic diagram of each type can be created for an element.
 * </p>
 * <p>
 * Automatic diagrams are meant to help analyze a design for maintenance, improvements or refactoring.
 * Two main categories exist:
 * <ul>
 * <li>Structure Diagrams, analyzing what an element contains, what it is.</li>
 * <li>Position Diagrams, analyzing what role an element has in a system, where it is.</li>
 * </ul>
 * </p>
 * @see IDiagramCreator
 * @since 2.2
 */
@objid ("ceb681b1-7e65-11e1-b95c-002564c97630")
public interface IAutoDiagramFactory {
    /**
     * Instanciates a new Diagram Creator for Class Structure diagrams.
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
     * @return a new Diagram Creator.
     */
    @objid ("ceb681b2-7e65-11e1-b95c-002564c97630")
    IDiagramCreator createClassStructureCreator();

    /**
     * Instanciates a new Diagram Creator for Inheritance diagrams.
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
     * @return a new Diagram Creator.
     */
    @objid ("ceb6a8c3-7e65-11e1-b95c-002564c97630")
    IDiagramCreator createInheritanceCreator();

    /**
     * Instanciates a new Diagram Creator for SubPackage Structure diagrams.
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
     * @return a new Diagram Creator.
     */
    @objid ("ceb6a8c5-7e65-11e1-b95c-002564c97630")
    IDiagramCreator createSubPackageStructureCreator();

    /**
     * Instanciates a new Diagram Creator for Package Content Structure diagrams.
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
     * @return a new Diagram Creator.
     */
    @objid ("ceb6a8c7-7e65-11e1-b95c-002564c97630")
    IDiagramCreator createPackageContentStructureCreator();

    /**
     * Instanciates a new Diagram Creator for Dependency diagrams.
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
     * @return a new Diagram Creator.
     */
    @objid ("ceb6a8c9-7e65-11e1-b95c-002564c97630")
    IDiagramCreator createDependencyCreator();

    /**
     * Instanciates a new Diagram Creator for Class Architecture diagrams.
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
     * @return a new Diagram Creator.
     */
    @objid ("e542ceff-7064-4206-8f6b-ec176947591d")
    IDiagramCreator createClassArchitectureCreator();

}
