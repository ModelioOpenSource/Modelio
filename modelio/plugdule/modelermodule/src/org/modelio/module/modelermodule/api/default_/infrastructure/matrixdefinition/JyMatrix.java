/* 
 * Copyright 2013-2019 Modeliosoft
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
 * Module: ModelerModule v9.0.07

 * This file was generated on 2/6/19 2:07 PM by Modelio Studio.
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
    @objid ("7a96e85e-ee49-4b5a-a3c0-fea2006f4c8b")
    public static final String STEREOTYPE_NAME = "JyMatrix";

    /**
     * The underlying {@link MatrixDefinition} represented by this proxy, never null.
     */
    @objid ("5b7881e5-7142-43c0-a813-da3eceabbdbd")
    protected final MatrixDefinition elt;

    /**
     * Tells whether a {@link JyMatrix proxy} can be instantiated from a {@link MObject} checking it is a {@link MatrixDefinition} stereotyped << JyMatrix >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * 
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("28990b46-37e2-462d-ab45-bcbdd206183c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MatrixDefinition) && ((MatrixDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, JyMatrix.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MatrixDefinition} stereotyped << JyMatrix >> then instantiate a {@link JyMatrix} proxy.
     * 
     * @return a {@link JyMatrix} proxy on the created {@link MatrixDefinition}.
     */
    @objid ("80078a5e-415b-4783-9093-ca3cd3e95535")
    public static JyMatrix create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("MatrixDefinition");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, JyMatrix.STEREOTYPE_NAME);
        return JyMatrix.instantiate((MatrixDefinition)e);
    }

    /**
     * Tries to instantiate a {@link JyMatrix} proxy from a {@link MatrixDefinition} stereotyped << JyMatrix >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a MatrixDefinition
     * @return a {@link JyMatrix} proxy or <i>null</i>.
     */
    @objid ("4a7c9767-3b84-4724-8356-fbbb6bf50e1d")
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
    @objid ("ffad12e4-e4ef-4721-9d0f-4a6f897c1bd6")
    public static JyMatrix safeInstantiate(MatrixDefinition obj) throws IllegalArgumentException {
        if (JyMatrix.canInstantiate(obj))
        	return new JyMatrix(obj);
        else
        	throw new IllegalArgumentException("JyMatrix: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("a16b5be8-c325-46b1-8435-5aeb05a2d04f")
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
    @objid ("13c648d5-d040-4299-849e-aa592576784d")
    public MatrixDefinition getElement() {
        return this.elt;
    }

    @objid ("cf152fb8-e94b-4eb0-9001-a7346fc14782")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d663a8e9-859b-45b9-a15a-5ab21946e01c")
    protected JyMatrix(MatrixDefinition elt) {
        this.elt = elt;
    }

    @objid ("c9f19dc4-d721-4fdd-80cf-cec8f0314a53")
    public static final class MdaTypes {
        @objid ("13ad967a-3694-4007-9032-c5ddc5410eac")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d60e3a48-a3fe-4fc3-9fe3-85d336197d05")
        private static Stereotype MDAASSOCDEP;

        @objid ("d2388382-694e-4c42-96ad-e399f11da467")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("f40608d2-feb1-43ea-b387-3d5636ae44b5")
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
