/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.infrastructure.profile;

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
import org.modelio.metamodel.uml.infrastructure.Profile;
import org.modelio.metamodel.uml.infrastructure.Stereotype;
import org.modelio.metamodel.uml.infrastructure.TagType;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyDefinition;
import org.modelio.metamodel.uml.infrastructure.properties.PropertyTableDefinition;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Profile} with << Methodology >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("d2cc83ce-07ab-4538-af69-8ed35787e831")
public class Methodology {
    @objid ("6912d75c-6a9a-40c6-986f-87eb9e6b8143")
    public static final String STEREOTYPE_NAME = "Methodology";

    /**
     * The underlying {@link Profile} represented by this proxy, never null.
     */
    @objid ("33235023-8f91-49aa-95f3-21e5bb9224d8")
    protected final Profile elt;

    /**
     * Tells whether a {@link Methodology proxy} can be instantiated from a {@link MObject} checking it is a {@link Profile} stereotyped << Methodology >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("9c0bc1a8-b2f0-4587-80ee-c4063cdb575e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Profile) && ((Profile) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Methodology.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Profile} stereotyped << Methodology >> then instantiate a {@link Methodology} proxy.
     * 
     * @return a {@link Methodology} proxy on the created {@link Profile}.
     */
    @objid ("b8f12307-143a-4664-b550-c91ea08c7f68")
    public static Methodology create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Profile");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Methodology.STEREOTYPE_NAME);
        return Methodology.instantiate((Profile)e);
    }

    /**
     * Tries to instantiate a {@link Methodology} proxy from a {@link Profile} stereotyped << Methodology >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Profile
     * @return a {@link Methodology} proxy or <i>null</i>.
     */
    @objid ("1ffeec4d-1488-4da4-9f15-1b8213b4dd2a")
    public static Methodology instantiate(Profile obj) {
        return Methodology.canInstantiate(obj) ? new Methodology(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Methodology} proxy from a {@link Profile} stereotyped << Methodology >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Profile}
     * @return a {@link Methodology} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("cf808b47-5459-49aa-a55e-65c682f4780e")
    public static Methodology safeInstantiate(Profile obj) throws IllegalArgumentException {
        if (Methodology.canInstantiate(obj))
        	return new Methodology(obj);
        else
        	throw new IllegalArgumentException("Methodology: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e2246852-bab3-435a-a75d-f4cc56fd2c18")
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
        Methodology other = (Methodology) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Profile}. 
     * @return the Profile represented by this proxy, never null.
     */
    @objid ("25ba4a69-d532-4ae6-94ff-067f8c86708b")
    public Profile getElement() {
        return this.elt;
    }

    @objid ("e3d80f1f-8781-47b3-a715-36d4543b33cb")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("208ab8a4-f95e-4039-aba9-b9c2c8d8b31f")
    protected Methodology(Profile elt) {
        this.elt = elt;
    }

    @objid ("9f7ace44-8e16-4801-b3e4-24f952f92343")
    public static final class MdaTypes {
        @objid ("0aa46ba1-eedf-473c-98e4-91451e8421c8")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("fa3c1531-a466-4eb0-8d71-81c2275abbea")
        private static Stereotype MDAASSOCDEP;

        @objid ("a6e22cbc-a343-4fea-aca0-3e56291579e9")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("cfa698c1-7544-49da-9a0b-69ff8efbf9c4")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "769b4d1b-614e-4503-892c-b59d0de23158");
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
