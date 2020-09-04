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
 * Proxy class to handle a {@link Event} with << UML2DestructionEvent >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("de52f393-67b1-475c-bd25-f72709e6e3a4")
public class UML2DestructionEvent {
    @objid ("a42c5e3e-8b4e-4aa3-83bc-6303b717d699")
    public static final String STEREOTYPE_NAME = "UML2DestructionEvent";

    /**
     * The underlying {@link Event} represented by this proxy, never null.
     */
    @objid ("0a00e629-5517-4619-87f7-6b8c2cf694d0")
    protected final Event elt;

    /**
     * Tells whether a {@link UML2DestructionEvent proxy} can be instantiated from a {@link MObject} checking it is a {@link Event} stereotyped << UML2DestructionEvent >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("8359a3dd-29eb-465d-b439-b00d53f56c7f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Event) && ((Event) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2DestructionEvent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Event} stereotyped << UML2DestructionEvent >> then instantiate a {@link UML2DestructionEvent} proxy.
     * 
     * @return a {@link UML2DestructionEvent} proxy on the created {@link Event}.
     */
    @objid ("6a96ee7b-2254-49bb-ab49-45c72e8e89b1")
    public static UML2DestructionEvent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Event");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2DestructionEvent.STEREOTYPE_NAME);
        return UML2DestructionEvent.instantiate((Event)e);
    }

    /**
     * Tries to instantiate a {@link UML2DestructionEvent} proxy from a {@link Event} stereotyped << UML2DestructionEvent >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Event
     * @return a {@link UML2DestructionEvent} proxy or <i>null</i>.
     */
    @objid ("3d4f7307-670f-49cb-b55c-a07016e68243")
    public static UML2DestructionEvent instantiate(Event obj) {
        return UML2DestructionEvent.canInstantiate(obj) ? new UML2DestructionEvent(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2DestructionEvent} proxy from a {@link Event} stereotyped << UML2DestructionEvent >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Event}
     * @return a {@link UML2DestructionEvent} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("1eebbcf3-05ba-45d2-80c9-12c504a50127")
    public static UML2DestructionEvent safeInstantiate(Event obj) throws IllegalArgumentException {
        if (UML2DestructionEvent.canInstantiate(obj))
        	return new UML2DestructionEvent(obj);
        else
        	throw new IllegalArgumentException("UML2DestructionEvent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("65675260-69c0-46b1-9b8d-235baae63ed8")
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
        UML2DestructionEvent other = (UML2DestructionEvent) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Event}. 
     * @return the Event represented by this proxy, never null.
     */
    @objid ("b9319bfc-8f3e-4378-9497-0f72949b9bd1")
    public Event getElement() {
        return this.elt;
    }

    @objid ("ebb93a13-ebd1-4013-8205-7ed85a742684")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("f278e240-603b-459e-902c-9693156cffe4")
    protected UML2DestructionEvent(Event elt) {
        this.elt = elt;
    }

    @objid ("9f8a454e-892f-45b5-bfe5-6ffdf6e1cbbc")
    public static final class MdaTypes {
        @objid ("c7e5b44d-fa14-42f1-9d59-66f9661f39aa")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("4ac349c5-b4b5-4b2e-9c48-5b7ad0476522")
        private static Stereotype MDAASSOCDEP;

        @objid ("4a527156-a42a-4ea1-8cdd-156ccc2d72b6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9a6788a0-a115-48d4-b5bd-5da1952475cd")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "c0f03827-5d0b-11df-a996-001302895b2b");
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
