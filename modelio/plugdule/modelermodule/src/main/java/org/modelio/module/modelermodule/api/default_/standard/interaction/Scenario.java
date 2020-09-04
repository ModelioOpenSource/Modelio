/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.interaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.interactionModel.Interaction;
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
 * Proxy class to handle a {@link Interaction} with << scenario >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("f5f40ae1-5948-4d16-9830-c685c68ebd5d")
public class Scenario {
    @objid ("05437dac-3e82-4968-a79c-9f237571aaf1")
    public static final String STEREOTYPE_NAME = "scenario";

    /**
     * The underlying {@link Interaction} represented by this proxy, never null.
     */
    @objid ("668b99a4-b19f-42cf-915e-c242d1f73f88")
    protected final Interaction elt;

    /**
     * Tells whether a {@link Scenario proxy} can be instantiated from a {@link MObject} checking it is a {@link Interaction} stereotyped << scenario >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("20ce744f-a7cd-4d71-a3b1-3102fbb008eb")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Interaction) && ((Interaction) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Scenario.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Interaction} stereotyped << scenario >> then instantiate a {@link Scenario} proxy.
     * 
     * @return a {@link Scenario} proxy on the created {@link Interaction}.
     */
    @objid ("0eb6a511-f77c-43a8-9e0a-92e117fc672d")
    public static Scenario create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Interaction");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Scenario.STEREOTYPE_NAME);
        return Scenario.instantiate((Interaction)e);
    }

    /**
     * Tries to instantiate a {@link Scenario} proxy from a {@link Interaction} stereotyped << scenario >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Interaction
     * @return a {@link Scenario} proxy or <i>null</i>.
     */
    @objid ("5290bbcb-1fcf-4f9d-a215-800d3f26ba11")
    public static Scenario instantiate(Interaction obj) {
        return Scenario.canInstantiate(obj) ? new Scenario(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Scenario} proxy from a {@link Interaction} stereotyped << scenario >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Interaction}
     * @return a {@link Scenario} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("af6ad949-4872-4a92-80c9-0fccf0a3f388")
    public static Scenario safeInstantiate(Interaction obj) throws IllegalArgumentException {
        if (Scenario.canInstantiate(obj))
        	return new Scenario(obj);
        else
        	throw new IllegalArgumentException("Scenario: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4db78980-48ae-48a3-95f2-3c89a9fec85b")
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
        Scenario other = (Scenario) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Interaction}. 
     * @return the Interaction represented by this proxy, never null.
     */
    @objid ("714deba2-1da4-4ddf-9461-9bb6d99b242c")
    public Interaction getElement() {
        return this.elt;
    }

    @objid ("3d16b1b6-7d4f-4adb-9d6b-b4f1e4535366")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("55f6e633-b5dd-4f07-9e95-78349c7e1de9")
    protected Scenario(Interaction elt) {
        this.elt = elt;
    }

    @objid ("4f33c4c3-47eb-4567-b0d2-04f872afd3f4")
    public static final class MdaTypes {
        @objid ("d1cfa88c-a5e8-4491-8970-94820193d061")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a2a0936a-a934-4b1d-aa32-0b2a6f3b328e")
        private static Stereotype MDAASSOCDEP;

        @objid ("6e590ca3-4fdb-4303-bfa7-e15b070d0fe8")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("84ddf6bf-b92b-4ceb-b806-a9d377df68b7")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec1ac4-0000-2eff-0000-000000000000");
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
