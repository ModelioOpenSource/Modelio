/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.class_;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.api.modelio.model.IModelingSession;
import org.modelio.api.modelio.model.PropertyConverter;
import org.modelio.api.module.context.IModuleContext;
import org.modelio.metamodel.mmextensions.infrastructure.ExtensionNotFoundException;
import org.modelio.metamodel.uml.infrastructure.Dependency;
import org.modelio.metamodel.uml.infrastructure.ModelElement;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Class} with << type >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("81f548a1-1daa-4886-a9f2-8c71e8080095")
public class Type {
    @objid ("1e6aa2c6-183f-446e-9c63-f637c396d0b4")
    public static final String STEREOTYPE_NAME = "type";

    /**
     * The underlying {@link Class} represented by this proxy, never null.
     */
    @objid ("c32b3dfa-9958-4dc4-904d-5bc2d1f11559")
    protected final Class elt;

    /**
     * Tells whether a {@link Type proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << type >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("44ba8b04-a8d2-4cf3-8d85-81a84ea1e3d1")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Class) && ((Class) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Type.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Class} stereotyped << type >> then instantiate a {@link Type} proxy.
     * 
     * @return a {@link Type} proxy on the created {@link Class}.
     */
    @objid ("14136715-af1f-4ad7-870f-a4b001279971")
    public static Type create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Class");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Type.STEREOTYPE_NAME);
        return Type.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link Type} proxy from a {@link Class} stereotyped << type >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link Type} proxy or <i>null</i>.
     */
    @objid ("6d00ac91-6a13-4a0b-a1b3-a74ee38cb27d")
    public static Type instantiate(Class obj) {
        return Type.canInstantiate(obj) ? new Type(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Type} proxy from a {@link Class} stereotyped << type >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Class}
     * @return a {@link Type} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d3a251af-1f85-4827-acce-8654d34d0b44")
    public static Type safeInstantiate(Class obj) throws IllegalArgumentException {
        if (Type.canInstantiate(obj))
        	return new Type(obj);
        else
        	throw new IllegalArgumentException("Type: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("41e2bd80-4d44-4a62-8219-6cca384f5d6c")
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
        Type other = (Type) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Class}. 
     * @return the Class represented by this proxy, never null.
     */
    @objid ("0168e126-4a79-40ca-9aa9-25c3c523f915")
    public Class getElement() {
        return this.elt;
    }

    @objid ("57e00b6b-aae0-4603-8b29-552ec5b02b4e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2bcc9526-1465-4b30-bc4e-55755c64a576")
    protected Type(Class elt) {
        this.elt = elt;
    }

    @objid ("efed0986-aaef-411c-afca-a6659ff2ccf9")
    public static final class MdaTypes {
        @objid ("9c33c5b6-dc9e-4212-8a23-a77897da14ed")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("73635d06-4868-4bce-bf98-998386f22123")
        private static Stereotype MDAASSOCDEP;

        @objid ("e1d8d50e-fbec-46f3-89d6-cecd7e993623")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c90615e8-1e45-40c1-8ab1-05dc2bb2b217")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01ba-0000-000000000000");
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
