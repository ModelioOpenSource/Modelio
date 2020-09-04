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
 * Proxy class to handle a {@link OpaqueAction} with << UML2ReadSelfAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("797d5347-a197-414c-84b4-44f2f877b47e")
public class UML2ReadSelfAction {
    @objid ("00865e52-a0a8-4a69-8a10-d3a1f6fded64")
    public static final String STEREOTYPE_NAME = "UML2ReadSelfAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("acd0d330-5a52-4ebf-812e-7cbb536484f8")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2ReadSelfAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2ReadSelfAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("5fd5d371-70ed-40a3-a8c8-035052203b05")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ReadSelfAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2ReadSelfAction >> then instantiate a {@link UML2ReadSelfAction} proxy.
     * 
     * @return a {@link UML2ReadSelfAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("9b336bbc-3e70-45b9-88b8-f081684c7028")
    public static UML2ReadSelfAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ReadSelfAction.STEREOTYPE_NAME);
        return UML2ReadSelfAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2ReadSelfAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadSelfAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2ReadSelfAction} proxy or <i>null</i>.
     */
    @objid ("6f7f8086-0aba-4525-a475-5b5a5506ad2e")
    public static UML2ReadSelfAction instantiate(OpaqueAction obj) {
        return UML2ReadSelfAction.canInstantiate(obj) ? new UML2ReadSelfAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ReadSelfAction} proxy from a {@link OpaqueAction} stereotyped << UML2ReadSelfAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2ReadSelfAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f6082e04-405d-49b7-9ce3-b1163fe74683")
    public static UML2ReadSelfAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2ReadSelfAction.canInstantiate(obj))
        	return new UML2ReadSelfAction(obj);
        else
        	throw new IllegalArgumentException("UML2ReadSelfAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("661cfd6c-457c-4de8-b3ba-dc844eb69674")
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
        UML2ReadSelfAction other = (UML2ReadSelfAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("88ae36ad-cba3-4aec-9a54-7a2999854e95")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("ca3bc3c4-1beb-4e33-8467-ee5321cf10e4")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2cbcc09b-7775-46fe-8fce-9b8756b23a3f")
    protected UML2ReadSelfAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("9b695724-53ea-403f-aee0-9d75de248162")
    public static final class MdaTypes {
        @objid ("cec4db7e-48b5-4667-bbcd-f0f8172e8ee5")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("85599a84-3430-4bfc-99ed-0fafb7ce0c6c")
        private static Stereotype MDAASSOCDEP;

        @objid ("2830913f-5120-4454-85ae-d61be7ae2755")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("8f7b3a6e-2742-45c6-b01d-2e21b95cdb13")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "120a35e7-c2fd-11de-8ac8-001302895b2b");
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
