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
 * Proxy class to handle a {@link Constraint} with << overlapping >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("56431f9b-f67b-4509-9a09-2227ea5aef29")
public class Overlapping {
    @objid ("c081a856-ddca-40cf-a348-8dac7df406ae")
    public static final String STEREOTYPE_NAME = "overlapping";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("6f4c2316-05c3-4995-832b-6fe3a0f63b14")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Overlapping proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << overlapping >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("2063ba03-1aa7-4900-9bc7-abe761b54cc6")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Overlapping.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << overlapping >> then instantiate a {@link Overlapping} proxy.
     * 
     * @return a {@link Overlapping} proxy on the created {@link Constraint}.
     */
    @objid ("883595c3-ffd1-4387-b3ef-afc37a554d5b")
    public static Overlapping create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Overlapping.STEREOTYPE_NAME);
        return Overlapping.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Overlapping} proxy from a {@link Constraint} stereotyped << overlapping >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Overlapping} proxy or <i>null</i>.
     */
    @objid ("f5008c85-3f8d-49f4-85ee-615a0f3a427d")
    public static Overlapping instantiate(Constraint obj) {
        return Overlapping.canInstantiate(obj) ? new Overlapping(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Overlapping} proxy from a {@link Constraint} stereotyped << overlapping >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Overlapping} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("b49e43e4-6a92-4c67-a506-f3456e76b592")
    public static Overlapping safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Overlapping.canInstantiate(obj))
        	return new Overlapping(obj);
        else
        	throw new IllegalArgumentException("Overlapping: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("32dc73e7-851e-42c6-9f21-90cf2c9b33c3")
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
        Overlapping other = (Overlapping) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("0e19e392-7d31-40aa-a557-0e12aa652735")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("aa24b00a-d42d-44bf-ae8a-c16b9e9cd63e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("35512a44-51ec-426f-b519-26c97360eacb")
    protected Overlapping(Constraint elt) {
        this.elt = elt;
    }

    @objid ("ae8f7c08-3dd0-4e2e-9abe-678cf39942a8")
    public static final class MdaTypes {
        @objid ("282ca9fe-5dde-4a97-9546-898926a7dbae")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("bfbdeebb-6e74-4ab2-9a51-08accebd1d20")
        private static Stereotype MDAASSOCDEP;

        @objid ("853847b6-f0f7-4bd6-8dce-b5f078f2d4c6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("9f144d66-1dcb-4bda-b663-ef76f2a55b36")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01fb-0000-000000000000");
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
