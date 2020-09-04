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
 * Proxy class to handle a {@link OpaqueAction} with << UML2SendObjectAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("1de174fc-1ed7-4452-9f8b-57656fbeb200")
public class UML2SendObjectAction {
    @objid ("959a7228-c193-4d8e-b76b-a5716e130c7b")
    public static final String STEREOTYPE_NAME = "UML2SendObjectAction";

    /**
     * The underlying {@link OpaqueAction} represented by this proxy, never null.
     */
    @objid ("2ebf9a9d-e9d3-4554-b3cf-d82bed87e786")
    protected final OpaqueAction elt;

    /**
     * Tells whether a {@link UML2SendObjectAction proxy} can be instantiated from a {@link MObject} checking it is a {@link OpaqueAction} stereotyped << UML2SendObjectAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("19720952-1e1d-4583-bca4-ad58a16474bd")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof OpaqueAction) && ((OpaqueAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2SendObjectAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link OpaqueAction} stereotyped << UML2SendObjectAction >> then instantiate a {@link UML2SendObjectAction} proxy.
     * 
     * @return a {@link UML2SendObjectAction} proxy on the created {@link OpaqueAction}.
     */
    @objid ("2934f946-2a95-487d-a896-9e2755a4192f")
    public static UML2SendObjectAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("OpaqueAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2SendObjectAction.STEREOTYPE_NAME);
        return UML2SendObjectAction.instantiate((OpaqueAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2SendObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2SendObjectAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a OpaqueAction
     * @return a {@link UML2SendObjectAction} proxy or <i>null</i>.
     */
    @objid ("1ff1864b-f0f2-4d27-a28c-584dfefd09de")
    public static UML2SendObjectAction instantiate(OpaqueAction obj) {
        return UML2SendObjectAction.canInstantiate(obj) ? new UML2SendObjectAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2SendObjectAction} proxy from a {@link OpaqueAction} stereotyped << UML2SendObjectAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link OpaqueAction}
     * @return a {@link UML2SendObjectAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9d8d9c37-687b-449f-a7fe-57ae5bba6670")
    public static UML2SendObjectAction safeInstantiate(OpaqueAction obj) throws IllegalArgumentException {
        if (UML2SendObjectAction.canInstantiate(obj))
        	return new UML2SendObjectAction(obj);
        else
        	throw new IllegalArgumentException("UML2SendObjectAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("daa846e4-5307-45b5-aa16-56622fe9744e")
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
        UML2SendObjectAction other = (UML2SendObjectAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link OpaqueAction}. 
     * @return the OpaqueAction represented by this proxy, never null.
     */
    @objid ("3a72641b-8c91-430c-a3fb-ec92a16f6fcb")
    public OpaqueAction getElement() {
        return this.elt;
    }

    @objid ("d93bc3e3-a491-4e04-83a3-d253dcfa7ca6")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("17c4c66c-274f-4dd2-8ab5-b777f8617367")
    protected UML2SendObjectAction(OpaqueAction elt) {
        this.elt = elt;
    }

    @objid ("66231600-a2af-46ef-9aea-d8405fd2e16e")
    public static final class MdaTypes {
        @objid ("2db3c03b-6144-456b-a83a-777cf35932f3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("795f74e1-00a3-4237-abfd-509f74ddd0fb")
        private static Stereotype MDAASSOCDEP;

        @objid ("fe7ad3ab-97a4-4b4a-bec2-7c6fbb444c40")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7ab96d51-f47b-44fe-83e0-0135a5211d25")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "52d7cccb-c2fd-11de-8ac8-001302895b2b");
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
