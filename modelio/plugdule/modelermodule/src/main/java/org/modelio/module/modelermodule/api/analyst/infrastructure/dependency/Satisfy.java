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
 * Proxy class to handle a {@link Dependency} with << satisfy >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("de664031-5286-4b77-8e34-8ad41e46aa5d")
public class Satisfy {
    @objid ("fde9db5a-0194-42d0-9176-c44539dc1ff0")
    public static final String STEREOTYPE_NAME = "satisfy";

    /**
     * The underlying {@link Dependency} represented by this proxy, never null.
     */
    @objid ("33cbc98d-c013-480e-b1c5-afdfefa87822")
    protected final Dependency elt;

    /**
     * Tells whether a {@link Satisfy proxy} can be instantiated from a {@link MObject} checking it is a {@link Dependency} stereotyped << satisfy >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("1d1d0b01-16d1-495e-876f-8930a4578306")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Dependency) && ((Dependency) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, Satisfy.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Dependency} stereotyped << satisfy >> then instantiate a {@link Satisfy} proxy.
     * 
     * @return a {@link Satisfy} proxy on the created {@link Dependency}.
     */
    @objid ("8007f4ed-fd39-4bcf-bce0-d96c2398bc0d")
    public static Satisfy create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Dependency");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, Satisfy.STEREOTYPE_NAME);
        return Satisfy.instantiate((Dependency)e);
    }

    /**
     * Tries to instantiate a {@link Satisfy} proxy from a {@link Dependency} stereotyped << satisfy >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Dependency
     * @return a {@link Satisfy} proxy or <i>null</i>.
     */
    @objid ("26a87295-376f-43bd-a3ff-c1addd3a35a8")
    public static Satisfy instantiate(Dependency obj) {
        return Satisfy.canInstantiate(obj) ? new Satisfy(obj) : null;
    }

    /**
     * Tries to instantiate a {@link Satisfy} proxy from a {@link Dependency} stereotyped << satisfy >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Dependency}
     * @return a {@link Satisfy} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("df78f126-795e-40c6-9795-71efa5fa2a8b")
    public static Satisfy safeInstantiate(Dependency obj) throws IllegalArgumentException {
        if (Satisfy.canInstantiate(obj))
        	return new Satisfy(obj);
        else
        	throw new IllegalArgumentException("Satisfy: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("026da7a8-1642-42d0-9ee9-b4fd8a0297b3")
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
        Satisfy other = (Satisfy) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Dependency}. 
     * @return the Dependency represented by this proxy, never null.
     */
    @objid ("77def765-5693-4322-acc5-0e2b9616305d")
    public Dependency getElement() {
        return this.elt;
    }

    @objid ("7a13dec1-dbff-4e34-8d8c-f0896f58f2db")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("42d06526-5c5d-41a9-b94c-8e3b938d2850")
    protected Satisfy(Dependency elt) {
        this.elt = elt;
    }

    @objid ("b06d31b4-20d4-42e2-b341-3bd59450e084")
    public static final class MdaTypes {
        @objid ("95ef0e26-6356-4858-8e62-0cafe8e0fe65")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("15b5238d-eeec-4172-af45-b7986593d96c")
        private static Stereotype MDAASSOCDEP;

        @objid ("ec508f80-8253-4f88-9aa0-f6500e0891b5")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("23359dc3-a664-4bbd-8f44-2fdd500e76a8")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "01ec12fc-0000-0224-0000-000000000000");
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
