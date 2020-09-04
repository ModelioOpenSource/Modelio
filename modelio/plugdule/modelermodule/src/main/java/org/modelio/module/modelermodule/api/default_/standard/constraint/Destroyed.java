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
 * Proxy class to handle a {@link Constraint} with << destroyed >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("5f98eedb-1fd0-40e9-8dd9-638944831a91")
public class Destroyed {
    @objid ("ad3ffc70-8af5-42ed-8478-05b58cd85074")
    public static final String STEREOTYPE_NAME = "destroyed";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("f5d2bee7-ad9a-49e1-a83d-a1d9f1c6f45b")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Destroyed proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << destroyed >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("b9242693-0caf-4230-8432-5c04908007e7")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Destroyed.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << destroyed >> then instantiate a {@link Destroyed} proxy.
     * 
     * @return a {@link Destroyed} proxy on the created {@link Constraint}.
     */
    @objid ("4bdb5696-843f-44d0-8932-75bf98e7ee17")
    public static Destroyed create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Destroyed.STEREOTYPE_NAME);
        return Destroyed.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Destroyed} proxy from a {@link Constraint} stereotyped << destroyed >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Destroyed} proxy or <i>null</i>.
     */
    @objid ("ba626429-9379-465f-8853-f3dd1169d30c")
    public static Destroyed instantiate(Constraint obj) {
        return Destroyed.canInstantiate(obj) ? new Destroyed(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Destroyed} proxy from a {@link Constraint} stereotyped << destroyed >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Destroyed} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("ea9c9145-2276-461e-8a77-d2f9a89c6246")
    public static Destroyed safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Destroyed.canInstantiate(obj))
        	return new Destroyed(obj);
        else
        	throw new IllegalArgumentException("Destroyed: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("4baa44bd-4dd5-43d5-8a4f-aaf39b03f0c9")
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
        Destroyed other = (Destroyed) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("d3b4a96a-8a4b-486e-a222-e39a49c844b7")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("b44ee017-b033-448f-b6e8-626b070dbbc0")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("2750cbb6-b7a4-495c-b4f1-85d9122e1b0f")
    protected Destroyed(Constraint elt) {
        this.elt = elt;
    }

    @objid ("74ba3d8d-a1db-491d-89b9-617b42eb8014")
    public static final class MdaTypes {
        @objid ("c8cac8f4-edab-4b12-99a5-805b7cc28754")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e1020b19-899a-4b3d-a930-d71921153407")
        private static Stereotype MDAASSOCDEP;

        @objid ("47341553-b42a-4b63-af0e-1bbd9da5071b")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("1877bb74-c7b3-403d-9bdc-576ea34f9a55")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01f3-0000-000000000000");
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
