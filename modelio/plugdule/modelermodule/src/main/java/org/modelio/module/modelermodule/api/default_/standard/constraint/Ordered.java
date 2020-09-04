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
 * Proxy class to handle a {@link Constraint} with << ordered >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("42a643e7-92ca-450c-bc7b-c44d1f137341")
public class Ordered {
    @objid ("8ee9ddb1-22f8-467d-a20f-683a4c81e32e")
    public static final String STEREOTYPE_NAME = "ordered";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("c4faf301-d83e-43c1-bfc5-c897f52de88c")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Ordered proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << ordered >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("d979106a-277c-499f-86eb-57e9484b3669")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Ordered.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << ordered >> then instantiate a {@link Ordered} proxy.
     * 
     * @return a {@link Ordered} proxy on the created {@link Constraint}.
     */
    @objid ("8270b90a-dce0-4b56-a93f-d76d9860450d")
    public static Ordered create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Ordered.STEREOTYPE_NAME);
        return Ordered.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Ordered} proxy from a {@link Constraint} stereotyped << ordered >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Ordered} proxy or <i>null</i>.
     */
    @objid ("3c116e4e-4275-412f-8064-c5a6d68b8d34")
    public static Ordered instantiate(Constraint obj) {
        return Ordered.canInstantiate(obj) ? new Ordered(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Ordered} proxy from a {@link Constraint} stereotyped << ordered >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Ordered} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("be8dfb8e-d3db-493d-a06e-3c0da0118a02")
    public static Ordered safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Ordered.canInstantiate(obj))
        	return new Ordered(obj);
        else
        	throw new IllegalArgumentException("Ordered: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f3ba6fd7-8f93-4c31-a399-fe9e47f81337")
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
        Ordered other = (Ordered) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("7d6bd32d-50cb-42d7-b45b-e84a169637a7")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("523962cc-ce6c-45d3-b59b-644de1d4f99c")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("7872732a-65ef-4793-b579-5778c7a5182b")
    protected Ordered(Constraint elt) {
        this.elt = elt;
    }

    @objid ("ce113c67-b988-43ce-b615-4a87a781f4e7")
    public static final class MdaTypes {
        @objid ("ab6867da-6b26-442e-b10c-830bc55a9dd1")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("98a9b3c3-14e3-437e-ab60-ce54c587e618")
        private static Stereotype MDAASSOCDEP;

        @objid ("8494b4fe-60fc-4be7-8ee3-a0394bc4a506")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("be42f6ab-a153-4859-a25f-ae26f80613e2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00540a84-0000-0003-0000-000000000000");
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
