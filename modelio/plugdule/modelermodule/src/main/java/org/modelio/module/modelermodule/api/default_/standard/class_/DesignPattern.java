/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.class_;

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
import org.modelio.metamodel.uml.statik.Class;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Class} with << design pattern >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("0485dbda-681e-4361-bcdb-4b0adb5be546")
public class DesignPattern {
    @objid ("068dc756-47d3-458c-ac48-adf451d4bb34")
    public static final String STEREOTYPE_NAME = "design pattern";

    /**
     * The underlying {@link Class} represented by this proxy, never null.
     */
    @objid ("151b6951-3128-472c-969d-1f002f5593c6")
    protected final Class elt;

    /**
     * Tells whether a {@link DesignPattern proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << design pattern >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("2f577562-24f6-4760-a9c5-72e3d2c8182e")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Class) && ((Class) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, DesignPattern.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Class} stereotyped << design pattern >> then instantiate a {@link DesignPattern} proxy.
     * 
     * @return a {@link DesignPattern} proxy on the created {@link Class}.
     */
    @objid ("dde30c6b-aa2f-4997-ad31-7af020aedb10")
    public static DesignPattern create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Class");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, DesignPattern.STEREOTYPE_NAME);
        return DesignPattern.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link DesignPattern} proxy from a {@link Class} stereotyped << design pattern >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link DesignPattern} proxy or <i>null</i>.
     */
    @objid ("4b31ee9e-a1fe-412f-b85e-70e8fd49a22e")
    public static DesignPattern instantiate(Class obj) {
        return DesignPattern.canInstantiate(obj) ? new DesignPattern(obj) : null;
    }

    /**
     * Tries to instantiate a {@link DesignPattern} proxy from a {@link Class} stereotyped << design pattern >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Class}
     * @return a {@link DesignPattern} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("82a4ecda-4052-4d5b-8d8b-a47e703ae8e5")
    public static DesignPattern safeInstantiate(Class obj) throws IllegalArgumentException {
        if (DesignPattern.canInstantiate(obj))
        	return new DesignPattern(obj);
        else
        	throw new IllegalArgumentException("DesignPattern: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("478ceb18-ae01-4406-9ce9-4d2ca74eea71")
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
        DesignPattern other = (DesignPattern) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Class}. 
     * @return the Class represented by this proxy, never null.
     */
    @objid ("e93c1963-564d-4e4e-b35a-961a2bfa5e11")
    public Class getElement() {
        return this.elt;
    }

    @objid ("efa3b69a-62ba-490f-aa08-5a34a2c8a6a7")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("b178adbb-7555-4c9e-8aab-957373fcdd8c")
    protected DesignPattern(Class elt) {
        this.elt = elt;
    }

    @objid ("c58585f8-0f0c-4e0b-82c0-d99f524b2dfc")
    public static final class MdaTypes {
        @objid ("b5621d78-916d-476e-b633-66cc16d0cfbb")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("dca81e09-bc40-481d-9d4b-b827706a6808")
        private static Stereotype MDAASSOCDEP;

        @objid ("80232672-0e1a-4735-b9f8-9872d62ccd12")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("5dfa0906-c746-48bd-a281-26d993f3121e")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "007006dc-0000-0139-0000-000000000000");
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
