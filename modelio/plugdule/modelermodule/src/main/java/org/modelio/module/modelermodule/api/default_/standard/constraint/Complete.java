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
 * Proxy class to handle a {@link Constraint} with << complete >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("949d7ce8-5626-43fd-86c7-a67be11c28f1")
public class Complete {
    @objid ("2dccd26f-96e5-49af-b0ce-688c64e2d911")
    public static final String STEREOTYPE_NAME = "complete";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("3a94babf-209a-43e5-9f58-2f4a0157d8b2")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Complete proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << complete >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("cb22ff6e-cdc2-4d8f-854e-78ddd518f561")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Complete.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << complete >> then instantiate a {@link Complete} proxy.
     * 
     * @return a {@link Complete} proxy on the created {@link Constraint}.
     */
    @objid ("16728291-f106-4665-94e1-1f5e8a684e96")
    public static Complete create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Complete.STEREOTYPE_NAME);
        return Complete.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Complete} proxy from a {@link Constraint} stereotyped << complete >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Complete} proxy or <i>null</i>.
     */
    @objid ("b898c165-fa3e-4bb3-b605-8f478d5db119")
    public static Complete instantiate(Constraint obj) {
        return Complete.canInstantiate(obj) ? new Complete(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Complete} proxy from a {@link Constraint} stereotyped << complete >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Complete} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("9a4ebce8-7e80-4eb2-950c-616eb4c4fa7e")
    public static Complete safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Complete.canInstantiate(obj))
        	return new Complete(obj);
        else
        	throw new IllegalArgumentException("Complete: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f51f8349-7718-440f-afc4-789c22f2dfe5")
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
        Complete other = (Complete) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("1e235b6f-0986-41b7-b844-8986e651ede2")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("c111ac2b-b6d4-4134-ab6b-e5e4cc276006")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("86ccd1df-8f2b-4d18-88a8-fe09177f5116")
    protected Complete(Constraint elt) {
        this.elt = elt;
    }

    @objid ("6ee7387e-71e0-4229-bfbd-e7a7e66d6948")
    public static final class MdaTypes {
        @objid ("001a68d7-9991-4187-8640-a8676685d55e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("875b35da-bf18-4a9b-9818-158cabe683e7")
        private static Stereotype MDAASSOCDEP;

        @objid ("5c4a11eb-ef68-46f0-ac4a-ef31d8f900ce")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("7ae95817-916e-4424-9262-d7f9f0c69fbc")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01f1-0000-000000000000");
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
