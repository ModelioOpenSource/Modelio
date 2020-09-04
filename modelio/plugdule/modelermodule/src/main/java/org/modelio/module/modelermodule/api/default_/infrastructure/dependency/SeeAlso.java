/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.infrastructure.dependency;

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
 * Proxy class to handle a {@link Dependency} with << see_also >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("dab2a566-5d9e-4d14-a078-3daa13429dd7")
public class SeeAlso {
    @objid ("3ac78a59-1d9e-45b4-a34a-f71648d3267d")
    public static final String STEREOTYPE_NAME = "see_also";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("7a83096b-22a9-4d19-89cc-95d18fadb0d4")
    protected final Dependency elt;

    /**
     * Tells whether a {@link SeeAlso proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << see_also >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("bade72e2-4f60-470f-930f-58676730a5ff")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, SeeAlso.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << see_also >> then instantiate a {@link SeeAlso} proxy.
     * 
     * @return a {@link SeeAlso} proxy on the created {@link Dependency}.
     */
    @objid ("ea3f0c09-75d6-4521-a0a0-c2c0b2017517")
    public static SeeAlso create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, SeeAlso.STEREOTYPE_NAME);
        return SeeAlso.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link SeeAlso} proxy from a {@link Dependency} stereotyped << see_also >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link SeeAlso} proxy or <i>null</i>.
     */
    @objid ("30f198c9-ae8d-4b07-a68e-2a65bf2b88e3")
    public static SeeAlso instantiate(Dependency obj) {
        return SeeAlso.canInstantiate(obj) ? new SeeAlso(obj) : null;
    }

    /**
     * Tries to instantiate a {@link SeeAlso} proxy from a {@link Dependency} stereotyped << see_also >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link SeeAlso} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("d304d151-5b33-4ccf-8ea9-6d1b20e5f47b")
    public static SeeAlso safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (SeeAlso.canInstantiate(obj))
        	return new SeeAlso(obj);
        else
        	throw new IllegalArgumentException("SeeAlso: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("94e123d3-9662-43bd-9642-fb9ee5f235a8")
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
        SeeAlso other = (SeeAlso) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("ec368da4-6bdb-4462-98fa-26b3744f338f")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("26179246-42f5-45f7-b33c-729a36900537")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("5880e803-7d66-46aa-a914-ae81362ae8a3")
    protected SeeAlso(Dependency elt) {
        this.elt = elt;
    }

    @objid ("5b47eb06-542a-4878-a3f2-5e7de42d4e66")
    public static final class MdaTypes {
        @objid ("fc2aa2c9-a64d-4e8c-9825-c31704fee307")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("8b1cd187-b37e-4f93-97c6-ad24f5b27f54")
        private static Stereotype MDAASSOCDEP;

        @objid ("674badbe-028d-4075-a3ad-6cf820d4c113")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("a0309257-d1b6-4b4d-a1f3-c6fdff43e478")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "0ac7e50f-50c6-4eb6-9107-3d9df92a2b75");
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
