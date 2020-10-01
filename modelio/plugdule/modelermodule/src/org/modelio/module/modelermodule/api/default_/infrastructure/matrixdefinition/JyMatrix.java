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
 * Module: ModelerModule v9.1.00

 * This file was generated on 3/2/20 11:26 AM by Modelio Studio.
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
    @objid ("e92ad0cc-7c3e-4ebb-b660-0102be1e241b")
    public static final String STEREOTYPE_NAME = "JyMatrix";

    /**
     * The underlying {@link MatrixDefinition} represented by this proxy, never null.
     */
    @objid ("bb753c53-d3b9-4115-b6f8-6b3e1a0d8a6f")
    protected final MatrixDefinition elt;

    /**
     * Tells whether a {@link JyMatrix proxy} can be instantiated from a {@link MObject} checking it is a {@link MatrixDefinition} stereotyped << JyMatrix >>.
     * <p>
     * The method returns <code>false</code> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <code>true</code> if the instantiation can be carried out else <code>false</code>.
     */
    @objid ("94141f74-b5d2-4de5-8a8a-f57e29f31f0d")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MatrixDefinition) && ((MatrixDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, JyMatrix.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MatrixDefinition} stereotyped << JyMatrix >> then instantiate a {@link JyMatrix} proxy.
     * 
     * @return a {@link JyMatrix} proxy on the created {@link MatrixDefinition}.
     */
    @objid ("d8176718-82c4-4f9e-b177-cb836c9181a6")
    public static JyMatrix create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MatrixDefinition");
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
    @objid ("945017fd-7dad-4210-9f5d-2ed0c2617b7b")
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
    @objid ("77aeeb4a-565e-456d-affb-e6de5a2d5610")
    public static JyMatrix safeInstantiate(MatrixDefinition obj) throws IllegalArgumentException {
        if (JyMatrix.canInstantiate(obj))
        	return new JyMatrix(obj);
        else
        	throw new IllegalArgumentException("JyMatrix: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f61b86b9-f2d1-4022-b813-d62e21dc8289")
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
    @objid ("deb2c8f7-ea94-4751-a95e-8383d0abed34")
    public MatrixDefinition getElement() {
        return this.elt;
    }

    @objid ("f38fa665-92ee-46ff-8209-362226ca1870")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("6523b7f3-9323-4687-ad01-f3248a2dce87")
    protected JyMatrix(MatrixDefinition elt) {
        this.elt = elt;
    }

    @objid ("c9f19dc4-d721-4fdd-80cf-cec8f0314a53")
    public static final class MdaTypes {
        @objid ("abc4e8e2-86f2-433f-a6aa-57c516bb69ce")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("28f7f073-a2b8-4adc-a8df-cf7c9e467c17")
        private static Stereotype MDAASSOCDEP;

        @objid ("476ba6ea-0f8f-4ff2-a6f2-428c16708a0d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("72f5fc10-6567-4a0e-95f0-4e66a13e99fc")
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
