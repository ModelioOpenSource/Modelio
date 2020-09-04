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
 * Proxy class to handle a {@link Constraint} with << disjoin >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("df267913-e3d3-4867-9a81-4c7c6893fae1")
public class Disjoin {
    @objid ("c45902e0-69e5-4181-a5cf-45af7891f035")
    public static final String STEREOTYPE_NAME = "disjoin";

    /**
     * The underlying {@link Constraint} represented by this proxy, never null.
     */
    @objid ("3182c688-6371-4df4-afb3-bf35b334de45")
    protected final Constraint elt;

    /**
     * Tells whether a {@link Disjoin proxy} can be instantiated from a {@link MObject} checking it is a {@link Constraint} stereotyped << disjoin >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("99991219-c45c-4313-9c9e-8c334193bf04")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Constraint) && ((Constraint) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Disjoin.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Constraint} stereotyped << disjoin >> then instantiate a {@link Disjoin} proxy.
     * 
     * @return a {@link Disjoin} proxy on the created {@link Constraint}.
     */
    @objid ("e60bd17f-167f-4914-9a3a-c9625b8851f9")
    public static Disjoin create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Constraint");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Disjoin.STEREOTYPE_NAME);
        return Disjoin.instantiate((Constraint)e);
    }

    /**
     * Tries to instantiate a {@link Disjoin} proxy from a {@link Constraint} stereotyped << disjoin >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Constraint
     * @return a {@link Disjoin} proxy or <i>null</i>.
     */
    @objid ("1c08912e-80de-4e0a-b0d3-971ed8ec7e5a")
    public static Disjoin instantiate(Constraint obj) {
        return Disjoin.canInstantiate(obj) ? new Disjoin(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Disjoin} proxy from a {@link Constraint} stereotyped << disjoin >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Constraint}
     * @return a {@link Disjoin} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("7d321ea7-cac6-429c-9067-b80947e032cd")
    public static Disjoin safeInstantiate(Constraint obj) throws IllegalArgumentException {
        if (Disjoin.canInstantiate(obj))
        	return new Disjoin(obj);
        else
        	throw new IllegalArgumentException("Disjoin: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("c5049ece-a323-4cb5-ad0e-cf332af24fbd")
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
        Disjoin other = (Disjoin) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Constraint}. 
     * @return the Constraint represented by this proxy, never null.
     */
    @objid ("4e45c242-c97b-4c53-b187-ff4e210d170b")
    public Constraint getElement() {
        return this.elt;
    }

    @objid ("d7beed47-0ea5-4044-a36f-2613047af939")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b350324a-3323-4cde-9109-1842e8daa1ea")
    protected Disjoin(Constraint elt) {
        this.elt = elt;
    }

    @objid ("27c0c9fd-b109-4914-a1a6-01ed7023ca30")
    public static final class MdaTypes {
        @objid ("bf52680c-b738-405b-8704-6667443dddc3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("94a9ce93-5f87-4ad2-9525-725692ff7ae1")
        private static Stereotype MDAASSOCDEP;

        @objid ("77618366-2f22-4a50-bfc4-f5cd524ecc2e")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("854ee358-80b4-4de0-b39c-1045fe98b30b")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01f5-0000-000000000000");
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
