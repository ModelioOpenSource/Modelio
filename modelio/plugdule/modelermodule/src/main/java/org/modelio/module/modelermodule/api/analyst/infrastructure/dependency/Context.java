/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.analyst.infrastructure.dependency;

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
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Dependency} with << context >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9adbf168-2b19-4e82-afe0-a6337867d437")
public class Context {
    @objid ("3d9806ed-bf91-49d4-95d7-52e6583c4c1b")
    public static final String STEREOTYPE_NAME = "context";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("8332dc07-8a40-4e62-b4f3-e2ba2093a3f0")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Context proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << context >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("b8c605dd-0e7d-499f-b0d9-471f140e2335")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Context.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << context >> then instantiate a {@link Context} proxy.
     * 
     * @return a {@link Context} proxy on the created {@link Dependency}.
     */
    @objid ("d15b4796-b19e-48d7-8534-df7b834f3fa1")
    public static Context create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Context.STEREOTYPE_NAME);
        return Context.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Context} proxy from a {@link Dependency} stereotyped << context >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Context} proxy or <i>null</i>.
     */
    @objid ("59d29e36-5831-4b91-8bd0-e5ac92ea03fd")
    public static Context instantiate(Dependency obj) {
        return Context.canInstantiate(obj) ? new Context(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Context} proxy from a {@link Dependency} stereotyped << context >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Context} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("70e10063-999c-4163-a72e-1a154b7b6054")
    public static Context safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Context.canInstantiate(obj))
        	return new Context(obj);
        else
        	throw new IllegalArgumentException("Context: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("aa177800-3087-4714-ac6b-84b8bfcfb9f6")
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
        Context other = (Context) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("2dabe917-067e-4a14-a4d9-4d2b0197d173")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("85bf2b7c-9408-43c1-bab7-a5828a5f4cf8")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("46771fbe-108a-4a25-bcfd-5c99199a801b")
    protected Context(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b5192799-2b87-4173-932c-3e67df3991ab")
    public static final class MdaTypes {
        @objid ("b2e25d94-40b4-4849-aea3-1e12c8ecae08")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("715ed484-50f4-4954-9b89-706d751ffbaa")
        private static Stereotype MDAASSOCDEP;

        @objid ("d10816d6-bf4d-4662-b980-0fc452406060")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("738a350a-5e42-4a5c-96be-815b7e35dc6f")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0242-0000-000000000000");
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
