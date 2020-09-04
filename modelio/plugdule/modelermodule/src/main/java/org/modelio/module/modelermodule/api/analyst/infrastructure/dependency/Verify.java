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
 * Proxy class to handle a {@link Dependency} with << verify >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("844b6b14-0353-4b18-9447-12baf0537b75")
public class Verify {
    @objid ("f93f8715-5ecf-4261-8411-cd0c57485479")
    public static final String STEREOTYPE_NAME = "verify";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("5c90af08-af83-47f6-a3db-d6a008ecdd60")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Verify proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << verify >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("659d8445-50f5-4c90-a0f4-7f93bc5cc796")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Verify.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << verify >> then instantiate a {@link Verify} proxy.
     * 
     * @return a {@link Verify} proxy on the created {@link Dependency}.
     */
    @objid ("3109caa2-3ab4-4193-b1d9-69909145bfc8")
    public static Verify create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Verify.STEREOTYPE_NAME);
        return Verify.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Verify} proxy from a {@link Dependency} stereotyped << verify >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Verify} proxy or <i>null</i>.
     */
    @objid ("c94ca7fb-e7f7-4f47-8cdf-b0a116b3f325")
    public static Verify instantiate(Dependency obj) {
        return Verify.canInstantiate(obj) ? new Verify(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Verify} proxy from a {@link Dependency} stereotyped << verify >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Verify} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("56a19242-ce35-4f2d-9004-3120957e9e07")
    public static Verify safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Verify.canInstantiate(obj))
        	return new Verify(obj);
        else
        	throw new IllegalArgumentException("Verify: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("f11c2a20-7ebd-45a6-9fdd-f7d082133616")
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
        Verify other = (Verify) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("a4bfe395-9815-4434-9277-1dc10734a121")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("972f04a0-e246-4f14-8fd4-a9963e578fba")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("0c66d754-1bb2-4877-a391-1f61343518e2")
    protected Verify(Dependency elt) {
        this.elt = elt;
    }

    @objid ("e2805429-a662-4e03-8705-adeea09fdcc7")
    public static final class MdaTypes {
        @objid ("8f3f802d-f53b-4131-aa7c-9afecb1ba0d0")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("e4dd6518-9b94-4470-b1a1-216f25184dc8")
        private static Stereotype MDAASSOCDEP;

        @objid ("90766a86-019f-4a55-9e80-b949273fd5b6")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("b2f11b15-6339-4762-b46f-d97e22c78fa2")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0229-0000-000000000000");
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
