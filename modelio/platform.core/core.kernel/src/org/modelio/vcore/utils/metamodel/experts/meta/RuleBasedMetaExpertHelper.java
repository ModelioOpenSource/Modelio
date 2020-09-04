/* 
 * Copyright 2013-2020 Modeliosoft
 * 
 * This file is part of Modelio.
 * 
 * Modelio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Modelio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with Modelio.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */

package org.modelio.vcore.utils.metamodel.experts.meta;

import java.util.HashSet;
import java.util.Set;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import org.modelio.vcore.smkernel.mapi.MClass;
import org.modelio.vcore.smkernel.mapi.MDependency;
import org.modelio.vcore.smkernel.mapi.MMetamodel;
import org.modelio.vcore.smkernel.mapi.MObject;
import org.modelio.vcore.smkernel.meta.SmClass;
import org.modelio.vcore.smkernel.meta.SmDependency;
import org.modelio.vcore.utils.metamodel.experts.IMetaExpertHelper;

/**
 * Default creation expert that uses metamodel rules to answer.
 */
@objid ("83e37b54-3327-427d-b7bc-737fc0215d42")
public class RuleBasedMetaExpertHelper implements IMetaExpertHelper {
    /**
     * This is for the moment a compilation flag that disable dependency rules
     * usage, until we really need it.
     * 
     * TODO : choose whether to keep this feature or not.
     */
    @objid ("1b5d4c84-6d3c-46f2-9d06-dcccf5e9fa23")
    private boolean useDependencyRules = false;

    @objid ("6f29db4f-5b2c-4eb5-a3ba-495fc5b40cbc")
    private final Rules dependencyRules;

    @objid ("c30cabec-2524-499d-ac64-31708796c1e4")
    private final Rules compositionRules;

    @objid ("fdfe81ff-f740-406e-ac53-33060b84a579")
    private final MMetamodel metamodel;

    @objid ("bbba37f3-0782-4e03-8d2d-f55ef938cf08")
    @Override
    public boolean canCompose(final MObject owner, final MObject composed, final String dep) {
        return canCompose(owner.getMClass(), composed.getMClass(), dep);
    }

    @objid ("dbf64b96-dc24-47d5-962d-3932d8a7078d")
    @Override
    public boolean canCompose(final MObject owner, final MClass composed, final String dep) {
        return canCompose(owner.getMClass(), composed, dep);
    }

    @objid ("299f249d-97f2-42bd-80b1-4fd730a72739")
    @Override
    public boolean canCompose(final MClass owner, final MClass composed, String dep) {
        SmClass smClass = (SmClass) owner;
        
        SmDependency smDep = dep != null ? smClass.getDependencyDef(dep) : (SmDependency) owner.getMetamodel().getMExpert().getDefaultCompositionDep(owner, composed);
        if (smDep != null) {
            return (smDep.isComposition() || smDep.isSharedComposition())
                    && composed.hasBase((smDep.getTarget()))
                    && this.compositionRules.isRule(owner, composed, smDep);
        } else {
            return this.compositionRules.isRule(owner, composed, null);
        }
    }

    @objid ("2469a8f5-45a6-4643-b055-b73b2dc2f1e9")
    @Override
    public boolean canDep(MObject source, MClass target, String dep) {
        return canDep(source.getMClass(), target, dep);
    }

    @objid ("5608989c-eadb-40cd-9c70-345250d52a34")
    @Override
    public boolean canDep(MClass source, MClass target, String dep) {
        MDependency mDep = source.getDependency(dep);
        if (mDep == null) {
            return false;
        } else if (this.useDependencyRules || mDep.isComposition() || mDep.isSharedComposition()) {
            return this.dependencyRules.isRule(source, target, mDep);
        } else {
            return target.hasBase(mDep.getTarget());
        }
    }

    @objid ("8dcac91d-ec44-4819-9b75-e6e4a8bf53fb")
    @Override
    public boolean canDep(MObject source, MObject target, String dep) {
        return canDep(source.getMClass(), target.getMClass(), dep);
    }

    /**
     * Register an allowed composition
     * 
     * @param parentClass the parent metaclass
     * @param childClass the child metaclass.
     * @param depName the composition dependency name
     */
    @objid ("2b602535-cd69-4d14-9dae-9dae58c69083")
    public void addCompositionRule(Class<? extends MObject> parentClass, Class<? extends MObject> childClass, String depName) {
        MClass msrc = checkedGetMetaclass(parentClass, "'%s' parent java interface does not match a metaclass");
        MClass mtarget = checkedGetMetaclass(childClass, "'%s' child java interface does not match a metaclass");
        
        this.compositionRules.addRule(msrc, mtarget, depName);
        if (mtarget != null) {
            this.compositionRules.addRule(msrc, mtarget, null);
        }
    }

    /**
     * Register an allowed composition
     * 
     * @param srcClass the source metaclass
     * @param targetClass the target metaclass.
     * @param depName the dependency name
     */
    @objid ("d1540701-031a-4dd3-8e7b-106323ca67ba")
    public void addDependencyRule(Class<? extends MObject> srcClass, Class<? extends MObject> targetClass, String depName) {
        MClass msrc = checkedGetMetaclass(srcClass, "'%s' source java interface does not match a metaclass");
        MClass mtarget = targetClass != null ? checkedGetMetaclass(targetClass, "'%s' target java interface does not match a metaclass") : null;
        
        this.dependencyRules.addRule(msrc, mtarget, depName);
        
        MDependency dep = msrc.getDependency(depName);
        if (dep == null)
            throw new IllegalArgumentException(String.format("'%s' metaclass does not have '%s' dependency", msrc.getQualifiedName(), depName));
        
        if (dep.isComposition() || dep.isSharedComposition()) {
            this.compositionRules.addRule(msrc, mtarget, depName);
            if (mtarget != null) {
                this.compositionRules.addRule(msrc, mtarget, null);
            }
        }
    }

    /**
     * @param mm the metamodel.
     */
    @objid ("eb12ae89-073f-47fb-b2e4-265fd28d0175")
    public RuleBasedMetaExpertHelper(MMetamodel mm) {
        this.metamodel = mm;
        this.compositionRules = new Rules();
        this.dependencyRules = new Rules();
    }

    /**
     * Activate/deactivate the dependency rules.
     * 
     * @param value whether or not to deactivate the rules.
     */
    @objid ("bae65db9-73ae-43b3-9d81-13c236e7b182")
    public void setUseDependencyRules(boolean value) {
        this.useDependencyRules = value;
    }

    @objid ("4918a1fa-6f48-4e00-9813-cd80c8c3b0f6")
    private MClass checkedGetMetaclass(Class<? extends MObject> parentClass, String errMsg) {
        MClass mc = this.metamodel.getMClass(parentClass);
        if (mc == null) {
            throw new IllegalArgumentException(String.format(errMsg, parentClass.getName()));
        }
        return mc;
    }

    /**
     * This class contains the metamodel knowledge of the {@link RuleBasedMetaExpertHelper}.
     * <p>
     * A CreationExpert tool can answer the following questions:
     * <ol>
     * <li>can I create an object X under an Y object ?</li>
     * <li>can I create a link D from an X to an Y</li>
     * <li>can I start a link D from an X</li>
     * </ol>
     * where X, Y are metaclass names and D a dependency name.
     * </p>
     */
    @objid ("a8839137-bbd3-4378-89f3-9d41c41dd98e")
    protected static class Rules {
        @objid ("ebea6212-7bc2-46ce-bd3c-7f0b5570a4bc")
        private final Set<RuleKey> directRules = new HashSet<>();

        @objid ("d6c9c19f-25bc-4805-a7a8-de389be32a8f")
        public Rules() {
        }

        /**
         * Tells whether the target metaclass can be added to the source metaclass with the given dependency.
         * 
         * @param srcMetaclass The owner metaclass
         * @param targetMetaclass The child metaclass
         * @param dep the dependency, if <i>null</i> answer for any dependency.
         * @return true if the creation is possible, false otherwise.
         */
        @objid ("c4dbfdce-0f5e-4727-a9ef-d93cef9304ac")
        public boolean isRule(MClass srcMetaclass, MClass targetMetaclass, MDependency dep) {
            String depName = dep != null ? dep.getName() : null;
            boolean ret = this.directRules.contains(new RuleKey(srcMetaclass, targetMetaclass, depName));
            
            ret |= this.directRules.contains(new RuleKey(srcMetaclass, null, depName));
            
            ret |= (dep != null) && this.directRules.contains(new RuleKey(dep.getSource(), null, depName)); // this may be useless
            return ret;
        }

        /**
         * Add an allowed rule.
         * 
         * @param mcX parent metaclass
         * @param mcY child metaclass
         * @param depName the dependency, if <i>null</i> answer for any dependency.
         */
        @objid ("15c9e4b6-ed2d-41ed-ab66-fcc72551ea3d")
        protected void addRule(MClass mcX, MClass mcY, String depName) {
            addRule(mcX, false, mcY, false, depName);
        }

        /**
         * Add an allowed rule.
         * 
         * @param srcClass source metaclass
         * @param withSrcSubclasses with subclasses
         * @param childClass the target metaclass
         * @param withChildSubclasses with target subclasses
         * @param depName the dependency, if <i>null</i> answer for any dependency.
         */
        @objid ("7d2e7163-d30f-49d0-976c-833db6bf0b29")
        protected void addRule(MClass srcClass, boolean withSrcSubclasses, MClass childClass, boolean withChildSubclasses, String depName) {
            RuleKey key = new RuleKey(srcClass, childClass, depName);
            
            this.directRules.add(key);
            
            if (withSrcSubclasses) {
                MClass mx = key.source;
                for (MClass xsub : mx.getSub(false)) {
                    addRule(xsub, true, childClass, withChildSubclasses, depName);
                }
            }
            
            if (withChildSubclasses) {
                MClass my = key.target;
                for (MClass ysub : my.getSub(false)) {
                    addRule(srcClass, withSrcSubclasses, ysub, true, depName);
                }
            }
        }

        @objid ("3f4cdfdf-5e75-4b21-a61e-092ee6abc59e")
        private static class RuleKey {
            @objid ("7781a394-b6d4-4ce5-8145-3d60ea8141b0")
            public final String depName;

            @objid ("c9bf971f-a4ff-40dd-af7a-48c9e557f6bb")
            public final MClass source;

            @objid ("902484d0-25b7-4d1b-97eb-26bea5e77326")
            public final MClass target;

            @objid ("4addc17d-f2ed-4fb8-a3cb-a98175c973d2")
            public RuleKey(MClass source, MClass target, String depName) {
                super();
                this.depName = depName;
                this.source = source;
                this.target = target;
            }

            @objid ("540a9dec-0833-42f8-9370-67254bd1ad51")
            @Override
            public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + ((this.depName == null) ? 0 : this.depName.hashCode());
                result = prime * result + ((this.source == null) ? 0 : this.source.hashCode());
                result = prime * result + ((this.target == null) ? 0 : this.target.hashCode());
                return result;
            }

            @objid ("2ef4cd92-6d59-4503-b497-1643b92a86ae")
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
                RuleKey other = (RuleKey) obj;
                if (this.depName == null) {
                    if (other.depName != null) {
                        return false;
                    }
                } else if (!this.depName.equals(other.depName)) {
                    return false;
                }
                if (this.source == null) {
                    if (other.source != null) {
                        return false;
                    }
                } else if (!this.source.equals(other.source)) {
                    return false;
                }
                if (this.target == null) {
                    if (other.target != null) {
                        return false;
                    }
                } else if (!this.target.equals(other.target)) {
                    return false;
                }
                return true;
            }

            @objid ("429622a7-1f83-44da-ac2c-b9f467d1eb87")
            @Override
            public String toString() {
                return "RuleKey [source=" + this.source + ", target=" + this.target + ", dep=" + this.depName + "]";
            }

        }

    }

}
