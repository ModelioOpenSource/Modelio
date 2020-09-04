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
 * Proxy class to handle a {@link ElementImport} with << friend >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("46045e22-e2ca-4093-b989-b9b9bfb21781")
public class Friend {
    @objid ("c8c9eacd-9166-40df-a01e-738988411310")
    public static final String STEREOTYPE_NAME = "friend";

    /**
     * The underlying {@link ElementImport} represented by this proxy, never null.
     */
    @objid ("86733689-3252-4ec5-8e01-ad1dbbf26386")
    protected final ElementImport elt;

    /**
     * Tells whether a {@link Friend proxy} can be instantiated from a {@link MObject} checking it is a {@link ElementImport} stereotyped << friend >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("c1c1f612-5cbc-4fa3-96dd-29a79dbf54eb")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof ElementImport) && ((ElementImport) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Friend.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link ElementImport} stereotyped << friend >> then instantiate a {@link Friend} proxy.
     * 
     * @return a {@link Friend} proxy on the created {@link ElementImport}.
     */
    @objid ("bb76c849-f88b-4f2e-aaa6-b5bc7b2be731")
    public static Friend create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("ElementImport");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Friend.STEREOTYPE_NAME);
        return Friend.instantiate((ElementImport)e);
    }

    /**
     * Tries to instantiate a {@link Friend} proxy from a {@link ElementImport} stereotyped << friend >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a ElementImport
     * @return a {@link Friend} proxy or <i>null</i>.
     */
    @objid ("7eed093c-ac4c-4bdd-af12-a528b4bb78ac")
    public static Friend instantiate(ElementImport obj) {
        return Friend.canInstantiate(obj) ? new Friend(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Friend} proxy from a {@link ElementImport} stereotyped << friend >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link ElementImport}
     * @return a {@link Friend} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("747c8d75-09c1-4554-b62e-6abbea01311a")
    public static Friend safeInstantiate(ElementImport obj) throws IllegalArgumentException {
        if (Friend.canInstantiate(obj))
        	return new Friend(obj);
        else
        	throw new IllegalArgumentException("Friend: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("067a471a-bd4f-4ac5-9a58-fc3708663d82")
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
        Friend other = (Friend) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link ElementImport}. 
     * @return the ElementImport represented by this proxy, never null.
     */
    @objid ("52486298-7618-475f-a003-b26ac07b7699")
    public ElementImport getElement() {
        return this.elt;
    }

    @objid ("073f250d-7d2a-4659-a63b-c4d8564284ce")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("9383a2be-4461-4d90-be6e-5ddd4accbd42")
    protected Friend(ElementImport elt) {
        this.elt = elt;
    }

    @objid ("e4135690-595b-43d4-b1e7-11c556195a6c")
    public static final class MdaTypes {
        @objid ("9ec70f57-17be-426a-90e5-0cc5636f4ec1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("60df508d-6823-4e18-b267-cf71b2d272cd")
        private static Stereotype MDAASSOCDEP;

        @objid ("ae36cc01-ae5f-42bc-b88b-9490ecd4b716")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a6356b62-825c-4eac-a9bc-c7e2eba2f685")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01ca-0000-000000000000");
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
