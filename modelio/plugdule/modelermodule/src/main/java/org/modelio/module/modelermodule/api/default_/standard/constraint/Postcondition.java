/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.constraint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Constraint;
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
 * Proxy class to handle a {@link Constraint} with << postcondition >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0cfb1227-3745-4c76-8e1a-0c23d0f7a8f4")
public class Postcondition {
    @objid ("6a81daad-8c6b-4284-82f1-c89e81b97fe7")
    public static final String STEREOTYPE_NAME = "postcondition";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("d0d6582a-a05d-4683-9cf9-224d968a285f")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Postcondition proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << postcondition >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("082acc8f-386b-4e69-8b35-949cb42af147")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Postcondition.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << postcondition >> then instantiate a {@link Postcondition} proxy.
     * 
     * @return a {@link Postcondition} proxy on the created {@link Constraint}.
     */
    @objid ("01c93257-3973-4848-b0f5-6453f2b00ba7")
    public static Postcondition create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Postcondition.STEREOTYPE_NAME);
        return Postcondition.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Postcondition} proxy from a {@link Constraint} stereotyped << postcondition >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Postcondition} proxy or <i>null</i>.
     */
    @objid ("b32ce9bb-943f-453e-a660-bd41a9f2cb56")
    public static Postcondition instantiate(Constraint obj) {
        return Postcondition.canInstantiate(obj) ? new Postcondition(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Postcondition} proxy from a {@link Constraint} stereotyped << postcondition >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Postcondition} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("f48c411c-c62a-492d-aede-69648f58791f")
    public static Postcondition safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Postcondition.canInstantiate(obj))
        	return new Postcondition(obj);
        else
        	throw new IllegalArgumentException("Postcondition: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("2ddfd0ed-a68a-4a7b-9729-00056d5c184d")
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
        Postcondition other = (Postcondition) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("b69e3b6a-749e-47f5-9c82-cf34fb8ffa22")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("7eb2a82f-1f2c-4576-b1c0-63bf354d1b3d")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("e18cfc3e-33cf-4422-a1d7-4f3b304e6d18")
    protected Postcondition(Constraint elt) {
        this.elt = elt;
    }

    @objid ("889980ab-e2c4-4755-bd2a-5c65867bd2d2")
    public static final class MdaTypes {
        @objid ("a58ac3f8-64f7-4601-998c-3fd258e654cc")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("5252e66b-e8a2-4722-9478-514c48804c04")
        private static Stereotype MDAASSOCDEP;

        @objid ("b03e353a-3f55-408c-a09e-7396ce75ee22")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("0cfb8d22-ba6b-4164-8f1d-36bf93cee958")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00000000-0000-9c46-0000-000000000000");
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
