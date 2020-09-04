/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.opaqueaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.OpaqueAction;
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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ValueSpecificationAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("dd53d800-09cd-4ca8-af69-5dce03bc0d85")
public class UML2ValueSpecificationAction {
    @objid ("bf32f79a-2eb7-4596-b9d9-879601b8060d")
    public static final String STEREOTYPE_NAME = "UML2ValueSpecificationAction";

    @objid ("c3fb639b-ec1d-4054-bad4-ffb1189877ce")
    public static final String VALUE_TAGTYPE = "Value";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("570b84bc-3ad0-4568-809a-c2fdf3812c1b")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ValueSpecificationAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ValueSpecificationAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("9a8b74b9-a1e6-4694-a3e1-ec33b68aac15")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ValueSpecificationAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ValueSpecificationAction >> then instantiate a {@link UML2ValueSpecificationAction} proxy.
     * 
     * @return a {@link UML2ValueSpecificationAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("0dbbd40f-a04c-4acc-81d2-58c90e236117")
    public static UML2ValueSpecificationAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ValueSpecificationAction.STEREOTYPE_NAME);
        return UML2ValueSpecificationAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ValueSpecificationAction} proxy from a {@link OpaqueAction} stereotyped << UML2ValueSpecificationAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ValueSpecificationAction} proxy or <i>null</i>.
     */
    @objid ("05394465-90ee-45c0-9f3e-31217265bb8a")
    public static UML2ValueSpecificationAction instantiate(OpaqueAction obj) {
        return UML2ValueSpecificationAction.canInstantiate(obj) ? new UML2ValueSpecificationAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ValueSpecificationAction} proxy from a {@link OpaqueAction} stereotyped << UML2ValueSpecificationAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ValueSpecificationAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f00dda45-5c2b-40c9-9cd2-bf0ba86dd844")
    public static UML2ValueSpecificationAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ValueSpecificationAction.canInstantiate(obj))
        	return new UML2ValueSpecificationAction(obj);
        else
        	throw new IllegalArgumentException("UML2ValueSpecificationAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("15d3bb92-8a01-45db-ad44-e60baf9a6e23")
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
        UML2ValueSpecificationAction other = (UML2ValueSpecificationAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("d3532c40-a22f-43da-a9f4-afd085e322f8")
    public OpaqueAction getElement() {
        return this.elt;
    }

    /**
     * Getter for string property 'Value'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("3dde82bc-d868-4ea0-9803-0fd6862de0b1")
    public String getValue() {
        return this.elt.getTagValue(UML2ValueSpecificationAction.MdaTypes.VALUE_TAGTYPE_ELT);
    }

    @objid ("f732f2bc-8f0a-45ec-91a1-88da5f5b493f")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    /**
     * Setter for string property 'Value'
     * <p>Property description:
     * <br/><i></i></p>
     */
    @objid ("b6e0da13-cb7d-4d5a-9dbc-523e3525a793")
    public void setValue(String value) {
        this.elt.putTagValue(UML2ValueSpecificationAction.MdaTypes.VALUE_TAGTYPE_ELT, value);
    }

    @objid ("f311dda4-9f5a-4535-b804-865a763414d7")
    protected UML2ValueSpecificationAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("3e1fa1c4-a0c2-4590-b736-9bac327ca525")
    public static final class MdaTypes {
        @objid ("b9714af6-6452-4622-9e71-55ced8927d04")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("90e58ecb-3976-4608-9725-f6b6211c54b1")
        public static TagType VALUE_TAGTYPE_ELT;

        @objid ("9987fa9d-7e2f-4ef2-b798-7e2121e1020a")
        private static Stereotype MDAASSOCDEP;

        @objid ("bc4e246b-a509-4582-abb6-139dfd9967d9")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("e19e42cc-d871-4734-961f-e7428443f4a6")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "1c0bf642-a90f-11de-8613-001302895b2b");
            VALUE_TAGTYPE_ELT = ctx.getModelingSession().findElementById(TagType.class, "b1935f69-ac36-11de-aefc-001302895b2b");
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
