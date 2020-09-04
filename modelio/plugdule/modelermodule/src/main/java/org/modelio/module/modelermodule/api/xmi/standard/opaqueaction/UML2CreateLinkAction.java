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
 * Proxy class to handle a {@link OpaqueAction} with << UML2CreateLinkAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("33dada2d-31ec-4a9d-9046-df7e11c23765")
public class UML2CreateLinkAction {
    @objid ("f42fbe15-1242-4682-a7e9-29089429810f")
    public static final String STEREOTYPE_NAME = "UML2CreateLinkAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("0b211beb-ef96-4651-8257-0ce72ac75c6b")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2CreateLinkAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2CreateLinkAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e7b59d2f-63ae-4228-973a-c48e9dc39b0b")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CreateLinkAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2CreateLinkAction >> then instantiate a {@link UML2CreateLinkAction} proxy.
     * 
     * @return a {@link UML2CreateLinkAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("b3acc449-0815-48fe-9345-f4ced65b3e1c")
    public static UML2CreateLinkAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CreateLinkAction.STEREOTYPE_NAME);
        return UML2CreateLinkAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2CreateLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateLinkAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2CreateLinkAction} proxy or <i>null</i>.
     */
    @objid ("74782fcb-36cb-4aec-a02e-4fb5088a960d")
    public static UML2CreateLinkAction instantiate(OpaqueAction obj) {
        return UML2CreateLinkAction.canInstantiate(obj) ? new UML2CreateLinkAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CreateLinkAction} proxy from a {@link OpaqueAction} stereotyped << UML2CreateLinkAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2CreateLinkAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("5b8d8c0b-edad-48d4-8349-b88cae9ac5fa")
    public static UML2CreateLinkAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2CreateLinkAction.canInstantiate(obj))
        	return new UML2CreateLinkAction(obj);
        else
        	throw new IllegalArgumentException("UML2CreateLinkAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f394bb60-0187-44e6-8d22-aa582f0922b2")
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
        UML2CreateLinkAction other = (UML2CreateLinkAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("537ea928-72c7-4431-b6d9-50881fd37f3e")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("6497873f-bcc0-4c0a-afd9-05b7c4b68fed")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f799c4ab-b8c7-46d2-a590-d05bc76cfb51")
    protected UML2CreateLinkAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("ff41ce0e-1a7a-41e4-b25f-3b3c0f13b830")
    public static final class MdaTypes {
        @objid ("00f53ba2-0a1d-48f0-9ba3-46452f15ee67")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("34d99dcf-15a3-42ea-ab69-5424816a482a")
        private static Stereotype MDAASSOCDEP;

        @objid ("8a0757de-9e76-4e43-a37c-cb77e250d487")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("2a948e1d-0241-48e1-9172-8a4acfbb2b25")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "67694a37-c2f9-11de-8ac8-001302895b2b");
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
