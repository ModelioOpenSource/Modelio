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
 * Proxy class to handle a {@link Class} with << implementationClass >> stereotype.
 * <p>Stereotype description:
 * <br/><i></i></p>
 */
@objid ("9a8d3476-0e47-4713-8073-82df91f999f5")
public class ImplementationClass {
    @objid ("bf27e55d-bf7c-4c2a-bdba-f2d5f14854ea")
    public static final String STEREOTYPE_NAME = "implementationClass";

    /**
     * The underlying {@link Class} represented by this proxy, never null.
     */
    @objid ("3d95ef9c-226f-4024-97f7-06bf0a67a68a")
    protected final Class elt;

    /**
     * Tells whether a {@link ImplementationClass proxy} can be instantiated from a {@link MObject} checking it is a {@link Class} stereotyped << implementationClass >>. 
     * <p>
     * The method returns <i>false</i> if the instantiation cannot be carried out.
     * @param elt a model object
     * @return <i>true</i> if the instantiation can be carried out else <i>false</i>.
     */
    @objid ("df0c2feb-9941-4625-8656-821f5fb496f5")
    public static boolean canInstantiate(MObject elt) {
        return ((elt instanceof Class) && ((Class) elt).isStereotyped(IModelerModulePeerModule.MODULE_NAME, ImplementationClass.STEREOTYPE_NAME));
    }

    /**
     * Create a new {@link Class} stereotyped << implementationClass >> then instantiate a {@link ImplementationClass} proxy.
     * 
     * @return a {@link ImplementationClass} proxy on the created {@link Class}.
     */
    @objid ("a0374048-f1e3-4607-9bba-a0fd5b246298")
    public static ImplementationClass create() {
        ModelElement e = (ModelElement)ModelerModuleModule.getInstance().getModuleContext().getModelingSession().getModel().createElement("Class");
        e.addStereotype(IModelerModulePeerModule.MODULE_NAME, ImplementationClass.STEREOTYPE_NAME);
        return ImplementationClass.instantiate((Class)e);
    }

    /**
     * Tries to instantiate a {@link ImplementationClass} proxy from a {@link Class} stereotyped << implementationClass >>checking its metaclass and its stereotype. 
     * <p>
     * The method returns <i>null</i> if the instantiation cannot be carried out.
     * @param obj a Class
     * @return a {@link ImplementationClass} proxy or <i>null</i>.
     */
    @objid ("0b520d36-172e-4d6b-9039-edb19407b9ba")
    public static ImplementationClass instantiate(Class obj) {
        return ImplementationClass.canInstantiate(obj) ? new ImplementationClass(obj) : null;
    }

    /**
     * Tries to instantiate a {@link ImplementationClass} proxy from a {@link Class} stereotyped << implementationClass >> checking its metaclass and its stereotype. 
     * <p>
     * The method throws an {@link IllegalArgumentException} if the instantiation cannot be carried out.
     * @param obj a {@link Class}
     * @return a {@link ImplementationClass} proxy.
     * @throws IllegalArgumentException if the instantiation cannot be carried out.
     */
    @objid ("320b35f1-90d6-45e3-9199-4795b644253b")
    public static ImplementationClass safeInstantiate(Class obj) throws IllegalArgumentException {
        if (ImplementationClass.canInstantiate(obj))
        	return new ImplementationClass(obj);
        else
        	throw new IllegalArgumentException("ImplementationClass: Cannot instantiate "+obj+": wrong element type or stereotype");
    }

    @objid ("1833c1c2-9e21-4593-8ab6-34f5f3c8b958")
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
        ImplementationClass other = (ImplementationClass) obj;
        return java.util.Objects.equals(getElement(), other.getElement());
    }

    /**
     * Get the underlying {@link Class}. 
     * @return the Class represented by this proxy, never null.
     */
    @objid ("b87147ef-2df9-456c-a74d-5bc1f5362915")
    public Class getElement() {
        return this.elt;
    }

    @objid ("119c3362-0f91-4269-ac6e-ebc64728a31e")
    @Override
    public int hashCode() {
        return 23 + ((this.elt == null) ? 0 : this.elt.hashCode());
    }

    @objid ("06d0fd59-37be-4d5b-9340-8276cf4cd834")
    protected ImplementationClass(Class elt) {
        this.elt = elt;
    }

    @objid ("1b7811d3-b98b-4855-a8c5-b056228fbf8f")
    public static final class MdaTypes {
        @objid ("a13bda90-9217-49e6-8232-98092d3614a3")
        public static Stereotype STEREOTYPE_ELT;

        @objid ("2e999579-c4cd-4ad4-a1c5-aa75df1ff5da")
        private static Stereotype MDAASSOCDEP;

        @objid ("8e9fad1a-77c1-4b60-b33a-2ff32a791537")
        private static TagType MDAASSOCDEP_ROLE;

        @objid ("bd7e8bad-043a-47e6-99af-773b15a47af1")
        public static void init(IModuleContext ctx) {
            STEREOTYPE_ELT = ctx.getModelingSession().findElementById(Stereotype.class, "00bc0050-0000-006b-0000-000000000000");
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
