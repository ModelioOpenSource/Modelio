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
 * Proxy class to handle a {@link OpaqueAction} with << UML2AddVariableValueAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("05c0cf23-e64d-48c8-b386-93a8c01ad74b")
public class UML2AddVariableValueAction {
    @objid ("e1f75963-326d-4a8a-aaed-812888484cd7")
    public static final String STEREOTYPE_NAME = "UML2AddVariableValueAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("f8452146-7494-493a-8502-162e0e044d34")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2AddVariableValueAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2AddVariableValueAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("7ec2b2a2-fd85-4820-9799-a865354714c4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2AddVariableValueAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2AddVariableValueAction >> then instantiate a {@link UML2AddVariableValueAction} proxy.
     * 
     * @return a {@link UML2AddVariableValueAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("8ff6c16e-27ea-460f-a42e-64c9601cfca6")
    public static UML2AddVariableValueAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2AddVariableValueAction.STEREOTYPE_NAME);
        return UML2AddVariableValueAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2AddVariableValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2AddVariableValueAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2AddVariableValueAction} proxy or <i>null</i>.
     */
    @objid ("4f0a561d-b537-44b0-bc95-78a242a67914")
    public static UML2AddVariableValueAction instantiate(OpaqueAction obj) {
        return UML2AddVariableValueAction.canInstantiate(obj) ? new UML2AddVariableValueAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2AddVariableValueAction} proxy from a {@link OpaqueAction} stereotyped << UML2AddVariableValueAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2AddVariableValueAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("a3180457-4a18-4768-9396-e12cffe0b209")
    public static UML2AddVariableValueAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2AddVariableValueAction.canInstantiate(obj))
        	return new UML2AddVariableValueAction(obj);
        else
        	throw new IllegalArgumentException("UML2AddVariableValueAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("5fd75384-0d1b-4111-9db2-2df2f96b9c36")
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
        UML2AddVariableValueAction other = (UML2AddVariableValueAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("f5c23a9b-75b1-4365-ac37-666acc37475c")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("d84d60c7-d376-4aeb-bf51-29cad09a557e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("51a3affa-f572-4c09-a019-d1484664c62d")
    protected UML2AddVariableValueAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("6292f25c-9e97-48f0-9eee-74b1c07894a1")
    public static final class MdaTypes {
        @objid ("51b4f5fb-8a48-469c-a533-4543b88c3579")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("d53b1c29-d6ae-4c44-ae51-3e573c787db9")
        private static Stereotype MDAASSOCDEP;

        @objid ("dca0f207-9e0d-4410-8f64-ac353c52e9ae")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("ac635e48-de71-4b48-ab79-524023541cfb")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "89326f2e-c2fc-11de-8ac8-001302895b2b");
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
