/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
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
    @objid ("f0039c9a-664b-4e1f-85b4-53ace39e2021")
    public static final String STEREOTYPE_NAME = "JyMatrix";

    /**
     * The underlying {@link MatrixDefinition} represented by this proxy, never null.
     */
    @objid ("0f5280f9-c7b6-4bb2-824d-da1cc2e31431")
    protected final MatrixDefinition elt;

    /**
     * Tells whether a {@link JyMatrix proxy} can be instantiated from a {@link MObject} checking it is a {@link MatrixDefinition} stereotyped << JyMatrix >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d8ebecf8-7223-478a-ba1e-076754de1497")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof MatrixDefinition) && ((MatrixDefinition) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, JyMatrix.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link MatrixDefinition} stereotyped << JyMatrix >> then instantiate a {@link JyMatrix} proxy.
     * 
     * @return a {@link JyMatrix} proxy on the created {@link MatrixDefinition}.
     */
    @objid ("13922529-0f8e-403f-93c2-ab77be2713ab")
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
    @objid ("c8442c6e-ee00-4a8a-ab73-764dc32927ba")
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
    @objid ("13627766-2155-4bba-b4d4-f26d364de2fa")
    public static JyMatrix safeInstantiate(MatrixDefinition obj) throws IllegalArgumentException {
        if (JyMatrix.canInstantiate(obj))
        	return new JyMatrix(obj);
        else
        	throw new IllegalArgumentException("JyMatrix: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("323d7b98-604f-4c0c-b778-fe475f96231c")
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
    @objid ("b50fb1aa-2252-4584-bafd-93593356e5ef")
    public MatrixDefinition getElement() {
        return this.elt;
    }

    @objid ("9bbdf83a-6ab1-49a7-89e0-23f1f55d5f11")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e089e2cb-00f1-44aa-98b9-a9baba0ce0dd")
    protected JyMatrix(MatrixDefinition elt) {
        this.elt = elt;
    }

    @objid ("c9f19dc4-d721-4fdd-80cf-cec8f0314a53")
    public static final class MdaTypes {
        @objid ("6ff1867b-fb09-4809-bb79-e01c7782e915")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("055a66d0-a787-4277-bf89-ff583fb9e7c5")
        private static Stereotype MDAASSOCDEP;

        @objid ("d14be5c7-8998-44e0-a882-b2e6022a6ad7")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1e2f822a-988d-41e9-ba2d-344761f12d6b")
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
