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
 * Proxy class to handle a {@link Event} with << UML2CreationEvent >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("16a2c5a1-3ebe-4d4a-92c9-14e29d2a36a8")
public class UML2CreationEvent {
    @objid ("de9c524b-5340-46f0-ab79-ca2be8805308")
    public static final String STEREOTYPE_NAME = "UML2CreationEvent";

    /**
     * The underlying {@link Event} represented by this proxy, never null.
     */
    @objid ("c6528198-ba57-4650-8541-aeb142fe6c2a")
    protected final Event elt;

    /**
     * Tells whether a {@link UML2CreationEvent proxy} can be instantiated from a {@link MObject} checking it is a {@link Event} stereotyped << UML2CreationEvent >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("dd8c260d-89af-4cef-a452-ff9628d214ba")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Event) && ((Event) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2CreationEvent.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Event} stereotyped << UML2CreationEvent >> then instantiate a {@link UML2CreationEvent} proxy.
     * 
     * @return a {@link UML2CreationEvent} proxy on the created {@link Event}.
     */
    @objid ("2a38d265-4d04-42af-9e61-8b91819bba7e")
    public static UML2CreationEvent create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Event");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2CreationEvent.STEREOTYPE_NAME);
        return UML2CreationEvent.instantiate((Event)e);
    }

    /**
     * Tries to instantiate a {@link UML2CreationEvent} proxy from a {@link Event} stereotyped << UML2CreationEvent >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Event
     * @return a {@link UML2CreationEvent} proxy or <i>null</i>.
     */
    @objid ("f785c30d-2a65-4d0c-8ff6-7e2cee76af06")
    public static UML2CreationEvent instantiate(Event obj) {
        return UML2CreationEvent.canInstantiate(obj) ? new UML2CreationEvent(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2CreationEvent} proxy from a {@link Event} stereotyped << UML2CreationEvent >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Event}
     * @return a {@link UML2CreationEvent} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("cb3ffc16-5112-4da7-8365-3400d10cdaf8")
    public static UML2CreationEvent safeInstantiate(Event obj) throws IllegalArgumentException {
        if (UML2CreationEvent.canInstantiate(obj))
        	return new UML2CreationEvent(obj);
        else
        	throw new IllegalArgumentException("UML2CreationEvent: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2f9582a3-1b6f-4dbe-b8dd-7eb138904e1d")
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
        UML2CreationEvent other = (UML2CreationEvent) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Event}. 
     * @return the Event represented by this proxy, never null.
     */
    @objid ("4f6c0574-4e9e-415b-a04d-a147097f7429")
    public Event getElement() {
        return this.elt;
    }

    @objid ("6709be03-a66f-4dd7-8537-4ad4690a1f79")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("eef0a2a0-1798-4679-8d11-68ed507ad841")
    protected UML2CreationEvent(Event elt) {
        this.elt = elt;
    }

    @objid ("ccd8e28f-0f71-4eec-bd00-ff6832d03c10")
    public static final class MdaTypes {
        @objid ("7315674d-6c6f-4f1f-b9e1-3bf2b1016791")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8bd5f132-fa1b-4d32-bed0-9b3afc34f743")
        private static Stereotype MDAASSOCDEP;

        @objid ("47314fa1-9552-4836-a2bb-b06c86604763")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("388b086f-4094-4630-b196-10a80ce12bdb")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "4edcbbef-5d0a-11df-a996-001302895b2b");
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
