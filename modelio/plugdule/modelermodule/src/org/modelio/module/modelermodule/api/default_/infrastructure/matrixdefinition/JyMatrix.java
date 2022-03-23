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
package org.modelio.module.modelermodule.api.default_.infrastructure.matrixdefinition;

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
import org.modelio.metamodel.uml.infrastructure.matrix.MatrixDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link MatrixDefinition} with << JyMatrix >> stereotype.
 * <p>Stereotype description:
 * <br/><i>Indicates a Matrix based on Jython expressions to compute its rows, columns, cell values.</i></p>
 */
@objid ("59e456e8-e2cc-46b3-a60f-40a9d4606127")
public class JyMatrix {
    @objid ("bef68c84-28eb-4658-a28f-96799ba80bdd")
    public static final String STEREOTYPE_NAME = "JyMatrix";

    /**
     * The underlying {@link MatrixDefinition} represented by this proxy, never null.
     */
    @objid ("e1da1a92-df03-4d62-b513-32ce5e06a936")
    protected final MatrixDefinition elt;

    /**
     * Tells whether a {@link JyMatrix proxy} can be instantiated from a {@link MObject} checking it is a {@link MatrixDefinition} stereotyped << JyMatrix >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("e666f8f3-0ee0-4020-9d1e-adef5b1a94c9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MatrixDefinition) && ((MatrixDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, JyMatrix.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MatrixDefinition} stereotyped << JyMatrix >> then instantiate a {@link JyMatrix} proxy.
     * 
     * @return a {@link JyMatrix} proxy on the created {@link MatrixDefinition}.
     */
    @objid ("d7df0721-243f-4066-9219-51f4ffc2bbd4")
    public static JyMatrix create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Infrastructure.MatrixDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, JyMatrix.STEREOTYPE_NAME);
        return JyMatrix.instantiate((MatrixDefinition)e);
    }

    /**
     * Tries to instantiate a {@link JyMatrix} proxy from a {@link MatrixDefinition} stereotyped << JyMatrix >> checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MatrixDefinition
     * @return a {@link JyMatrix} proxy or <i>null</i>.
     */
    @objid ("4dee86aa-85b8-4876-b0a6-9805a99babc3")
    public static JyMatrix instantiate(MatrixDefinition obj) {
        return JyMatrix.canInstantiate(obj) ? new JyMatrix(obj) : null;
    }

    /**
     * Tries to instantiate a {@link JyMatrix} proxy from a {@link MatrixDefinition} stereotyped << JyMatrix >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link MatrixDefinition}
     * @return a {@link JyMatrix} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a3c4d00f-0df1-4a0c-a727-a2c610331773")
    public static JyMatrix safeInstantiate(MatrixDefinition obj) throws IllegalArgumentException {
        if (JyMatrix.canInstantiate(obj))
        	return new JyMatrix(obj);
        else
        	throw new IllegalArgumentException("JyMatrix: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("316cb338-2d9f-4d92-ad6e-322629b2a3e9")
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
        JyMatrix other = (JyMatrix) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link MatrixDefinition}. 
     * @return the MatrixDefinition represented by this proxy, never null.
     */
    @objid ("c5ed038a-6f0b-49e6-9d37-4a2c4d860bca")
    public MatrixDefinition getElement() {
        return this.elt;
    }

    @objid ("7b123e71-1978-40f0-985f-d003d9c2d545")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
        
    }

    @objid ("3772eb70-a5cf-466c-9a29-5bb5b4224008")
    protected  JyMatrix(MatrixDefinition elt) {
        this.elt = elt;
    }

    @objid ("c9f19dc4-d721-4fdd-80cf-cec8f0314a53")
    public static final class MdaTypes {
        @objid ("c2933757-30dc-4657-abd8-b1cdfd8bbf03")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("c15a50d1-57ab-419c-92f1-0a292e536d53")
        private static Stereotype MDAASSOCDEP;

        @objid ("a65d96dc-627e-4b57-9653-1e89b2583d3c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6cc90beb-3cef-4b78-8b29-1814b5cadef9")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "72ada667-0b7f-4421-bd69-9b037642ed87");
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
