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
/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.3.00

 * This file was generated on 10/8/20 2:50 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << related_diagram >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("fca5b424-314d-4acd-b390-d416296df62e")
public class RelatedDiagram {
    @objid ("19eaeabe-02f5-42c9-9212-5fb740a15d47")
    public static final String STEREOTYPE_NAME = "related_diagram";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("7fd4dd25-2685-428f-a535-59428e51b0e0")
    protected final Dependency elt;

    /**
     * Tells whether a {@link RelatedDiagram proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << related_diagram >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("0e450482-a97e-4215-88f7-6e3ffb996d29")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, RelatedDiagram.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << related_diagram >> then instantiate a {@link RelatedDiagram} proxy.
     * 
     * @return a {@link RelatedDiagram} proxy on the created {@link Dependency}.
     */
    @objid ("eaadb6ba-ea37-4286-b45e-042da899af5d")
    public static RelatedDiagram create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, RelatedDiagram.STEREOTYPE_NAME);
        return RelatedDiagram.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link RelatedDiagram} proxy from a {@link Dependency} stereotyped << related_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link RelatedDiagram} proxy or <i>null</i>.
     */
    @objid ("3c7df4b4-1190-4ef1-be2a-b58c9a949447")
    public static RelatedDiagram instantiate(Dependency obj) {
        return RelatedDiagram.canInstantiate(obj) ? new RelatedDiagram(obj) : null;
    }

    /**
     * Tries to instantiate a {@link RelatedDiagram} proxy from a {@link Dependency} stereotyped << related_diagram >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link RelatedDiagram} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6862d731-b97e-4041-bc46-5b2e536ec48f")
    public static RelatedDiagram safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (RelatedDiagram.canInstantiate(obj))
        	return new RelatedDiagram(obj);
        else
        	throw new IllegalArgumentException("RelatedDiagram: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("8c94e569-6cf9-4a34-bd31-81b08725d0d9")
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        RelatedDiagram other = (RelatedDiagram) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("167ca596-c55a-4ed6-8102-7f337c1f91da")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("867fd44c-bf39-4d2d-9712-04db5f934c3a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("c156aa09-9ef9-43cc-a726-bca7ff24e6e6")
    protected  RelatedDiagram(Dependency elt) {
        this.elt = elt;
    }

    @objid ("7c15b3f3-14bd-419f-b399-40c4a720d7a2")
    public static final class MdaTypes {
        @objid ("f0588890-e109-4ec9-b3e9-9bfd2f76dc8e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("25cd217c-94c5-4665-8185-7c3e24192014")
        private static Stereotype MDAASSOCDEP;

        @objid ("cefedc36-b68c-4154-b653-b85c028087b6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7da1f51b-7096-40c9-ae31-0f3a472c1d6d")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "2961d57b-5120-11de-bbaf-00218648fa3d");
            MDAASSOCDEP = ctx.getModelingSession().findElementById(Stereotype.class, "94b7efa5-f94c-4d1d-896f-f103e56a8e2e");
            MDAASSOCDEP_ROLE = ctx.getModelingSession().findElementById(TagType.class, "7637f2fd-b750-43c1-a15c-5d0b084ca1cd");
            
        }
	static {
        		if(ModelerModuleModule.getInstance() != null) {
        			init(ModelerModuleModule.getInstance().getModuleContext());
        		}
        	}
        
    }

}
