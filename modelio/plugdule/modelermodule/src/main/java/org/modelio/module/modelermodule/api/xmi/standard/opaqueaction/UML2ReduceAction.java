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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReduceAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f1c94473-28e1-4fe7-ac2d-cd800cd05029")
public class UML2ReduceAction {
    @objid ("70fce72f-2857-4c45-8c1c-69718777984f")
    public static final String STEREOTYPE_NAME = "UML2ReduceAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("2bf97d71-efd5-44f5-b8a7-4919e818f658")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReduceAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReduceAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("dad4d48c-4911-4b99-9bf1-d1874b4aeebc")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReduceAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReduceAction >> then instantiate a {@link UML2ReduceAction} proxy.
     * 
     * @return a {@link UML2ReduceAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("5b67d7c4-a29f-463a-a3f0-12fbc2ff20d3")
    public static UML2ReduceAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReduceAction.STEREOTYPE_NAME);
        return UML2ReduceAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReduceAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReduceAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReduceAction} proxy or <i>null</i>.
     */
    @objid ("f06cf470-33eb-4e14-9faa-db9cea971999")
    public static UML2ReduceAction instantiate(OpaqueAction obj) {
        return UML2ReduceAction.canInstantiate(obj) ? new UML2ReduceAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReduceAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReduceAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReduceAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("de5337c6-8f19-4e7c-834a-0372d31ae799")
    public static UML2ReduceAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReduceAction.canInstantiate(obj))
        	return new UML2ReduceAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReduceAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("06eaea82-60ce-4241-bd4c-96ec8fccc17e")
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
        UML2ReduceAction other = (UML2ReduceAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("88dbcf82-2241-47f3-b6fd-54e74f4596fb")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("76ff9675-49fe-476c-abb0-73eaa570292a")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("ac0f201e-f4a8-4714-a1d8-a4d4cfb7cf42")
    protected UML2ReduceAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("bb02c893-e717-4243-aea0-fd3720614737")
    public static final class MdaTypes {
        @objid ("1960e0a0-e6a0-4c6a-85d8-5fbf536249cd")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("f4152780-7906-40eb-9b0a-23e64f09ff91")
        private static Stereotype MDAASSOCDEP;

        @objid ("5256f64c-1cff-4fcb-9b54-3ee79a19ca66")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("6f998e92-62a9-4e9b-bb2a-4e6da44ccc4f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "2eb4ec1b-c2fd-11de-8ac8-001302895b2b");
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
