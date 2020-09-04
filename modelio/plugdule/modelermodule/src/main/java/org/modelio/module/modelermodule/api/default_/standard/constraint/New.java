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
 * Proxy class to handle a {@link Constraint} with << new >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0b720f13-4154-4751-a90f-aadaef5fa9dd")
public class New {
    @objid ("4b6c4954-dfef-41c9-97a9-8086501ab826")
    public static final String STEREOTYPE_NAME = "new";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("c9e0f9f0-0b4c-488e-a8f1-4cf644d047da")
    protected final Constraint elt;

    /**
     * Tells whether a {@link New proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << new >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("3144b534-706b-4de9-a609-9a29a817f9ee")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, New.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << new >> then instantiate a {@link New} proxy.
     * 
     * @return a {@link New} proxy on the created {@link Constraint}.
     */
    @objid ("8a536d7d-d26a-4d8c-85bb-f259e3d0ecab")
    public static New create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, New.STEREOTYPE_NAME);
        return New.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link New} proxy from a {@link Constraint} stereotyped << new >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link New} proxy or <i>null</i>.
     */
    @objid ("2f204bac-3b43-4ed5-a0a4-f2dfc4d38316")
    public static New instantiate(Constraint obj) {
        return New.canInstantiate(obj) ? new New(obj) : null;
    }

    /**
     * Tries to instantiate a {@link New} proxy from a {@link Constraint} stereotyped << new >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link New} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("673c316e-099c-4f6e-b1a8-b4deadbac1bb")
    public static New safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (New.canInstantiate(obj))
        	return new New(obj);
        else
        	throw new IllegalArgumentException("New: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("670ac837-23f2-40bb-a8e2-edc0f54500ea")
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
        New other = (New) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("e0438615-ada3-4008-b636-fa0ed60ec93b")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("a044fed5-270a-4e8f-8705-33d3737c111c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("75131a93-4287-4d74-ad88-53e664d0556e")
    protected New(Constraint elt) {
        this.elt = elt;
    }

    @objid ("2f9de4ba-2666-4917-a258-dbdafc9f0143")
    public static final class MdaTypes {
        @objid ("5f7236ce-3939-472b-8ada-8944bb1073fa")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("3ceaad02-127e-4297-8528-8a6bb04a1c31")
        private static Stereotype MDAASSOCDEP;

        @objid ("eec3954d-0c08-4cc9-a562-c2a18bab309c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("61dd701a-3645-40cb-ae51-c9ad089d5d11")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01f9-0000-000000000000");
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
