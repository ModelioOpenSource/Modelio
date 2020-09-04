/*
 * WARNING: GENERATED FILE - DO NOT EDIT
 * Module: ModelerModule v9.0.06

 * This file was generated on 10/3/18 5:28 PM by Modelio Studio.
 */
package org.modelio.module.modelermodule.api.default_.standard.classifier;

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
import org.modelio.metamodel.uml.statik.Classifier;
import org.modelio.module.modelermodule.api.IModelerModulePeerModule;
import org.modelio.module.modelermodule.api.ModelerModuleProxyFactory;
import org.modelio.module.modelermodule.impl.ModelerModuleModule;
import org.modelio.vcore.smkernel.mapi.MObject;

/**
 * Proxy class to handle a {@link Classifier} with << utility >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("361f955a-46bb-4174-ae99-420074e6c928")
public class Utility {
    @objid ("6d5f62b6-579a-4e20-b18a-5a4b0ccf2123")
    public static final String STEREOTYPE_NAME = "utility";

    /**
     * The underlying {@link Classifier} represented by this proxy, never null.
     */
    @objid ("c4100aca-c295-4c09-bac0-abf9fc8d648a")
    protected final Classifier elt;

    /**
     * Tells whether a {@link Utility proxy} can be instantiated from a {@link MObject} checking it is a {@link Classifier} stereotyped << utility >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("e46f9fe7-c281-4ec7-9257-3cbb0010e7f9")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Classifier) && ((Classifier) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Utility.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Classifier} stereotyped << utility >> then instantiate a {@link Utility} proxy.
     * 
     * @return a {@link Utility} proxy on the created {@link Classifier}.
     */
    @objid ("fd75b512-ff6a-4944-97c1-7332f5be1ff8")
    public static Utility create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Classifier");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Utility.STEREOTYPE_NAME);
        return Utility.instantiate((Classifier)e);
    }

    /**
     * Tries to instantiate a {@link Utility} proxy from a {@link Classifier} stereotyped << utility >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Classifier
     * @return a {@link Utility} proxy or <i>null</i>.
     */
    @objid ("ca27268e-2f29-4065-9ae7-198d5f9c2407")
    public static Utility instantiate(Classifier obj) {
        return Utility.canInstantiate(obj) ? new Utility(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Utility} proxy from a {@link Classifier} stereotyped << utility >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Classifier}
     * @return a {@link Utility} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("66356727-985a-4d10-8e2b-eca69d6b9909")
    public static Utility safeInstantiate(Classifier obj) throws IllegalArgumentException {
        if (Utility.canInstantiate(obj))
        	return new Utility(obj);
        else
        	throw new IllegalArgumentException("Utility: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("e45f3794-325a-40a5-b184-b4bbaff639df")
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
        Utility other = (Utility) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Classifier}. 
     * @return the Classifier represented by this proxy, never null.
     */
    @objid ("e80a2a98-131a-4680-8fd9-20ace4b40ac9")
    public Classifier getElement() {
        return this.elt;
    }

    @objid ("d98e04d8-5309-4b4a-837f-df8a9da06e35")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("6149b179-eda1-4c69-a393-676d608fd2e6")
    protected Utility(Classifier elt) {
        this.elt = elt;
    }

    @objid ("0a6fc122-40b5-4691-a722-7d1756955c7a")
    public static final class MdaTypes {
        @objid ("05d1411f-4d5c-4477-b084-c6304cae4494")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("a9aec4e8-499d-411a-b034-a53a6860577d")
        private static Stereotype MDAASSOCDEP;

        @objid ("b03e0323-c198-43e5-ade4-db4b744e0cc5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("c481f04e-4185-4ed9-b4a6-d07c7a2b91ac")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00700680-0000-01bf-0000-000000000000");
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
