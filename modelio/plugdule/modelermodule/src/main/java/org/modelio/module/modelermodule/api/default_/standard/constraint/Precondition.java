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
 * Proxy class to handle a {@link Constraint} with << precondition >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("193679c9-0aaf-479a-b4f9-10dfca5f464d")
public class Precondition {
    @objid ("61b09ec6-ae8a-44f8-85d4-66b79bc6d335")
    public static final String STEREOTYPE_NAME = "precondition";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("cc10c8a4-4219-4985-90b8-3434ad4c83f1")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Precondition proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << precondition >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("bb0ac57f-3823-4020-a1bb-0094b4996e5f")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Precondition.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << precondition >> then instantiate a {@link Precondition} proxy.
     * 
     * @return a {@link Precondition} proxy on the created {@link Constraint}.
     */
    @objid ("70abe187-fdf6-42c0-bd5a-c69d4570c1db")
    public static Precondition create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Precondition.STEREOTYPE_NAME);
        return Precondition.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Precondition} proxy from a {@link Constraint} stereotyped << precondition >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Precondition} proxy or <i>null</i>.
     */
    @objid ("e1e1d1ee-3358-4a6b-946e-01dcd714224c")
    public static Precondition instantiate(Constraint obj) {
        return Precondition.canInstantiate(obj) ? new Precondition(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Precondition} proxy from a {@link Constraint} stereotyped << precondition >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Precondition} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("5a9878e8-2baf-4f48-ac05-2cd729eda21a")
    public static Precondition safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Precondition.canInstantiate(obj))
        	return new Precondition(obj);
        else
        	throw new IllegalArgumentException("Precondition: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("b2947779-af45-46e5-8ac4-4c2ca16a4d4d")
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
        Precondition other = (Precondition) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("b7dcc8c2-7ce1-4f4f-9791-196f66daaf22")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("adfa3dda-0207-4a31-a59b-334e26dbd968")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("c38cc4d5-062d-4535-a305-1df50217627f")
    protected Precondition(Constraint elt) {
        this.elt = elt;
    }

    @objid ("7643ddc4-75b0-4f0c-88a7-b7292095f7e2")
    public static final class MdaTypes {
        @objid ("7cc1c8ed-ac1e-4bad-b9ec-0dcb6a3bb42e")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("ae273d6b-2d7d-457f-a86c-2a989ff33874")
        private static Stereotype MDAASSOCDEP;

        @objid ("394b793d-eee4-43c5-af3d-93ca45edea3c")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("4af4a8aa-640e-4a88-adf0-1cd9cf3ba146")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00000000-0000-9c45-0000-000000000000");
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
