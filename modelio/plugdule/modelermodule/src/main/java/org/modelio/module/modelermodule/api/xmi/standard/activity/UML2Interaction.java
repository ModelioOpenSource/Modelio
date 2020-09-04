/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.xmi.standard.activity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.behavior.activityModel.Activity;
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
 * Proxy class to handle a {@link Activity} with << UML2Interaction  >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("fe0a2eb7-94ca-4d4c-b8b4-0755c72af361")
public class UML2Interaction {
    @objid ("7a40f93c-3d84-4243-aa84-5394e8e85ce6")
    public static final String STEREOTYPE_NAME = "UML2Interaction ";

    /**
     * The underlying {@link Activity} represented by this proxy, never null.
     */
    @objid ("8d3820d1-d963-43ab-98c0-fbdbecc0422c")
    protected final Activity elt;

    /**
     * Tells whether a {@link UML2Interaction proxy} can be instantiated from a {@link MObject} checking it is a {@link Activity} stereotyped << UML2Interaction  >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e99f4366-3bbd-4aa4-8489-d1b695aa91c9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Activity) && ((Activity) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, UML2Interaction.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Activity} stereotyped << UML2Interaction  >> then instantiate a {@link UML2Interaction} proxy.
     * 
     * @return a {@link UML2Interaction} proxy on the created {@link Activity}.
     */
    @objid ("a3f4bbef-74f8-4daa-b767-f0be82457cf0")
    public static UML2Interaction create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Activity");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, UML2Interaction.STEREOTYPE_NAME);
        return UML2Interaction.instantiate((Activity)e);
    }

    /**
     * Tries to instantiate a {@link UML2Interaction} proxy from a {@link Activity} stereotyped << UML2Interaction  >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Activity
     * @return a {@link UML2Interaction} proxy or <i>null</i>.
     */
    @objid ("adb24415-7cc1-4aae-9da2-a443e7ae6457")
    public static UML2Interaction instantiate(Activity obj) {
        return UML2Interaction.canInstantiate(obj) ? new UML2Interaction(obj) : null;
    }

    /**
     * Tries to instantiate a {@link UML2Interaction} proxy from a {@link Activity} stereotyped << UML2Interaction  >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Activity}
     * @return a {@link UML2Interaction} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("6217fa49-6d59-4b15-acd8-60290bf51563")
    public static UML2Interaction safeInstantiate(Activity obj) throws IllegalArgumentException {
        if (UML2Interaction.canInstantiate(obj))
        	return new UML2Interaction(obj);
        else
        	throw new IllegalArgumentException("UML2Interaction: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("25f5f32a-18a1-49e4-8ee0-c38c1f6be6cc")
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
        UML2Interaction other = (UML2Interaction) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Activity}. 
     * @return the Activity represented by this proxy, never null.
     */
    @objid ("c0a42b92-da1e-4660-813d-59ab73f0125a")
    public Activity getElement() {
        return this.elt;
    }

    @objid ("de44b7a7-2ee3-427e-ab69-beb1ae73680b")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c2c59b56-c7d5-4202-96fd-f9aea6cfc5c0")
    protected UML2Interaction(Activity elt) {
        this.elt = elt;
    }

    @objid ("2d8755d9-18eb-44df-b006-43b75366bab0")
    public static final class MdaTypes {
        @objid ("68460f68-897d-4420-b94f-40ac895b543a")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("b066acbd-5c5c-4dbe-8a53-1f0177873405")
        private static Stereotype MDAASSOCDEP;

        @objid ("0d9ad9df-ce48-4f47-84aa-c168080a175d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b6eebd7b-f19b-434a-a557-357c99b75cbf")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "021863a9-f3db-11df-8ada-0027103f347c");
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
