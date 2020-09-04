/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.event;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.commonBehaviors.Event;
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
 * Proxy class to handle a {@link Event} with << UML2ExecutionEvent >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b2ec965b-ef55-4971-a1f2-5637918175da")
public class UML2ExecutionEvent {
    @objid ("5c183d25-64e6-40aa-9833-4054a236907b")
    public static final String STEREOTYPE_NAME = "UML2ExecutionEvent";

    /**
     * The underlying {@link Event} represented by this proxy, never null.
     */
    @objid ("88308873-5ea3-49dd-911a-1a7594b69af8")
    protected final Event elt;

    /**
     * Tells whether a {@link UML2ExecutionEvent proxy} can be instantiated from a {@link MObject} checking it is a {@link Event} stereotyped << UML2ExecutionEvent >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("b0987e1e-84ef-4daa-95e1-60adfb3f7161")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Event) && ((Event) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2ExecutionEvent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Event} stereotyped << UML2ExecutionEvent >> then instantiate a {@link UML2ExecutionEvent} proxy.
     * 
     * @return a {@link UML2ExecutionEvent} proxy on the created {@link Event}.
     */
    @objid ("07b1627a-a95f-4e7d-9b43-d2a9f527df36")
    public static UML2ExecutionEvent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Event");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2ExecutionEvent.STEREOTYPE_NAME);
        return UML2ExecutionEvent.instantiate((Event)e);
    }

    /**
     * Tries to instantiate a {@link UML2ExecutionEvent} proxy from a {@link Event} stereotyped << UML2ExecutionEvent >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Event
     * @return a {@link UML2ExecutionEvent} proxy or <i>null</i>.
     */
    @objid ("24f345bf-77a5-405f-9277-cb40878348d2")
    public static UML2ExecutionEvent instantiate(Event obj) {
        return UML2ExecutionEvent.canInstantiate(obj) ? new UML2ExecutionEvent(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2ExecutionEvent} proxy from a {@link Event} stereotyped << UML2ExecutionEvent >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Event}
     * @return a {@link UML2ExecutionEvent} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("07dcaf73-3585-4aad-98bb-445bfd733273")
    public static UML2ExecutionEvent safeInstantiate(Event obj) throws IllegalArgumentException {
        if (UML2ExecutionEvent.canInstantiate(obj))
        	return new UML2ExecutionEvent(obj);
        else
        	throw new IllegalArgumentException("UML2ExecutionEvent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("7d56fc1f-368a-416c-bedf-a25059818cd1")
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
        UML2ExecutionEvent other = (UML2ExecutionEvent) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Event}. 
     * @return the Event represented by this proxy, never null.
     */
    @objid ("6ba08121-a9bc-4b4b-b9e1-ced5be66606c")
    public Event getElement() {
        return this.elt;
    }

    @objid ("6e8561d8-997e-4b25-ae94-3e84f4300240")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("d62b81cb-6568-4fc7-9273-7857630f2981")
    protected UML2ExecutionEvent(Event elt) {
        this.elt = elt;
    }

    @objid ("f3b61e58-3dcd-44f6-852c-419944d58ac1")
    public static final class MdaTypes {
        @objid ("91336769-6b90-4e08-acb1-25efc8f975e2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("1f424a24-c4dc-4cb5-b683-5fd337d283f8")
        private static Stereotype MDAASSOCDEP;

        @objid ("377e46c6-b9ce-4ff0-b184-055084b580c3")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("feba354d-75b2-4868-b034-afeb3ed796a2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "15d2108f-5d0c-11df-a996-001302895b2b");
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
