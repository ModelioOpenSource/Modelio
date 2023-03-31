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
package org.modelio.api.modelio.model.scope;

import com.modeliosoft.modelio.javadesigner.annotations.mdl;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.vcore.smkernel.mapi.MClass;

/**
 * Mutable version of {@link ElementScope}.
 */
@objid ("f47a4a70-b632-42b7-8f98-a69dccc3ff4b")
public class MutableElementScope {
    
    @mdl.prop
    @objid ("1d9c2864-63a6-45b3-b5ce-b50052b79f4d")
    public boolean withSubClasses;

    @mdl.propgetter
    public boolean isWithSubClasses() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.withSubClasses;
    }

    @mdl.propsetter
    public void setWithSubClasses(boolean value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.withSubClasses = value;
    }

    
    @mdl.prop
    @objid ("2b8201f9-1b0b-4d1c-8eab-0496be550d59")
    public boolean withSubStereotypes;

    @mdl.propgetter
    public boolean isWithSubStereotypes() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.withSubStereotypes;
    }

    @mdl.propsetter
    public void setWithSubStereotypes(boolean value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.withSubStereotypes = value;
    }

    @objid ("b8fef5ae-c519-44c7-9a93-135aa8a9c399")
    private MClass metaclass;

    
    @mdl.prop
    @objid ("6bc29587-dbf0-4149-95b0-e4e3c1ea3624")
    public Stereotype stereotype;

    @mdl.propgetter
    public Stereotype getStereotype() {
        // Automatically generated method. Please delete this comment before entering specific code.
        return this.stereotype;
    }

    @mdl.propsetter
    public void setStereotype(Stereotype value) {
        // Automatically generated method. Please delete this comment before entering specific code.
        this.stereotype = value;
    }

    @objid ("481b2352-2be5-4912-be84-cda2058ff57b")
    public  MutableElementScope() {
        super();
    }

    @objid ("215825c1-d314-4dfe-8f3e-0dada31f7723")
    public  MutableElementScope(ElementScope s) {
        this.metaclass = s.getMetaclass();
        this.stereotype = s.getStereotype();
        this.withSubClasses = s.isWithSubClasses();
        this.withSubStereotypes = s.isWithSubStereotypes();
        
    }

    @objid ("ff7d9091-abe2-4107-8da8-09817db86fce")
    public  MutableElementScope(MClass metaclass, Stereotype stereotype) {
        this.metaclass = metaclass;
        this.stereotype = stereotype;
        
    }

    @objid ("18aae530-9307-4e03-9ff3-65984d6e1469")
    public ElementScope toElementScope() {
        return new ElementScope(getMetaclass(), isWithSubClasses(), getStereotype(), isWithSubStereotypes());
    }

    @objid ("918b8f56-b2bc-4c7b-83a7-12d90b88cdae")
    public MClass getMetaclass() {
        return this.metaclass;
    }

    @objid ("056a82be-f8fc-4158-bb7e-e2ec87ac0071")
    public void setMetaclass(final MClass value) {
        this.metaclass = value;
    }

}
