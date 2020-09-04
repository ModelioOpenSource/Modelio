/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.sendsignalaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.SendSignalAction;
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
 * Proxy class to handle a {@link SendSignalAction} with << UML2BroadcastSignalAction >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("912daf5b-02b1-4804-a635-9b0de80c403f")
public class UML2BroadcastSignalAction {
    @objid ("727e7417-fe36-4631-97d9-a6bf920a19f9")
    public static final String STEREOTYPE_NAME = "UML2BroadcastSignalAction";

    /**
     * The underlying {@link SendSignalAction} represented by this proxy, never null.
     */
    @objid ("c1ac41ef-3566-4fb4-af2a-1cbb5d5e95c2")
    protected final SendSignalAction elt;

    /**
     * Tells whether a {@link UML2BroadcastSignalAction proxy} can be instantiated from a {@link MObject} checking it is a {@link SendSignalAction} stereotyped << UML2BroadcastSignalAction >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("0834213d-8e35-4a1a-8989-69b13f38d6a4")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof SendSignalAction) && ((SendSignalAction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2BroadcastSignalAction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link SendSignalAction} stereotyped << UML2BroadcastSignalAction >> then instantiate a {@link UML2BroadcastSignalAction} proxy.
     * 
     * @return a {@link UML2BroadcastSignalAction} proxy on the created {@link SendSignalAction}.
     */
    @objid ("f25c3aa5-9fcf-4cd0-8360-9f2caf65559d")
    public static UML2BroadcastSignalAction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("SendSignalAction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2BroadcastSignalAction.STEREOTYPE_NAME);
        return UML2BroadcastSignalAction.instantiate((SendSignalAction)e);
    }

    /**
     * Tries to instantiate a {@link UML2BroadcastSignalAction} proxy from a {@link SendSignalAction} stereotyped << UML2BroadcastSignalAction >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a SendSignalAction
     * @return a {@link UML2BroadcastSignalAction} proxy or <i>null</i>.
     */
    @objid ("b1ed6eaa-a8ba-48cc-89af-83b220c8f42d")
    public static UML2BroadcastSignalAction instantiate(SendSignalAction obj) {
        return UML2BroadcastSignalAction.canInstantiate(obj) ? new UML2BroadcastSignalAction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2BroadcastSignalAction} proxy from a {@link SendSignalAction} stereotyped << UML2BroadcastSignalAction >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link SendSignalAction}
     * @return a {@link UML2BroadcastSignalAction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("5db8035e-70c2-4896-bcd5-9342053084b3")
    public static UML2BroadcastSignalAction safeInstantiate(SendSignalAction obj) throws IllegalArgumentException {
        if (UML2BroadcastSignalAction.canInstantiate(obj))
        	return new UML2BroadcastSignalAction(obj);
        else
        	throw new IllegalArgumentException("UML2BroadcastSignalAction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("07881c6c-7c5a-40ff-bd84-1e0b9342865a")
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
        UML2BroadcastSignalAction other = (UML2BroadcastSignalAction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link SendSignalAction}. 
     * @return the SendSignalAction represented by this proxy, never null.
     */
    @objid ("8a9a203a-9c3d-48b0-8e5d-458c21483316")
    public SendSignalAction getElement() {
        return this.elt;
    }

    @objid ("8723db43-bd28-4f98-8dcc-61d38d0f26b2")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("adbb946c-cc78-4703-a6bb-326bafffff81")
    protected UML2BroadcastSignalAction(SendSignalAction elt) {
        this.elt = elt;
    }

    @objid ("e18ef05a-6696-4bc4-a862-c1d144cb1230")
    public static final class MdaTypes {
        @objid ("35138dd3-4a1f-49bd-8d8e-58d544f4435d")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("16d3ccb6-8dab-49c9-b883-2a007d5b11b1")
        private static Stereotype MDAASSOCDEP;

        @objid ("e71fb0a0-5532-4163-bbb4-a8e820c6bb21")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("d522b506-c50e-4c41-b950-c19e43b44ba4")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "edc05471-5d08-11df-a996-001302895b2b");
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
