/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.elementimport;

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
import org.modelio.metamodel.uml.statik.ElementImport;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link ElementImport} with << access >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("b374025a-7e25-4fab-adc2-20468c9497a7")
public class Access {
    @objid ("854539f9-c279-4d6a-9d87-f73ba28ab189")
    public static final String STEREOTYPE_NAME = "access";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("ba0163cf-a078-4858-84f7-ca99e8c6e3c4")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Access proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << access >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("8391ecfa-c913-48be-a14e-fb7bdd4a3a8c")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Access.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << access >> then instantiate a {@link Access} proxy.
     * 
     * @return a {@link Access} proxy on the created {@link ElementImport}.
     */
    @objid ("6c19f719-a399-4b5a-8c8f-2ec69c533ee2")
    public static Access create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Access.STEREOTYPE_NAME);
        return Access.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Access} proxy from a {@link ElementImport} stereotyped << access >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Access} proxy or <i>null</i>.
     */
    @objid ("a177c301-5d42-46ab-ac98-433f2fabff18")
    public static Access instantiate(ElementImport obj) {
        return Access.canInstantiate(obj) ? new Access(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Access} proxy from a {@link ElementImport} stereotyped << access >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Access} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("0659acf5-2041-421d-a967-9c7c142a2fb8")
    public static Access safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Access.canInstantiate(obj))
        	return new Access(obj);
        else
        	throw new IllegalArgumentException("Access: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("ef8d251f-2333-4aee-92e7-f8cfe47a2308")
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
        Access other = (Access) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("9dd08ac0-14b5-4956-94bd-3c7f6d246336")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("94608dd2-77fd-4cee-8286-9d80573a1067")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("76400b82-7c59-4a59-931d-87bcdf8f544e")
    protected Access(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("f1f67b2c-6db9-48c7-8cfe-811ff7873676")
    public static final class MdaTypes {
        @objid ("f4d1d116-32bc-447d-aa02-f59f08ae37f2")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("64b6d145-c2e6-4411-8a26-fd8ce4a8de84")
        private static Stereotype MDAASSOCDEP;

        @objid ("ee4e1589-d768-4496-a85b-51ce589e326d")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("774fcd6d-ec33-43dc-9fce-099cce1cd925")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01cc-0000-000000000000");
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
