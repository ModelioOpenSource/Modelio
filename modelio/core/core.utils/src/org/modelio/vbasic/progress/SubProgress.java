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
package org.modelio.vbasic.progress;

import java.util.Objects;
import java.util.function.Supplier;
import com.modeliosoft.modelio.javadesigner.annotations.objid;

/**
 * <p>A progress monitor that uses a given amount of work ticks from a parent monitor. This is intended as a
 * safer, easier-to-use alternative to SubProgressMonitor. The main benefits of SubProgress over
 * SubProgressMonitor are:</p>
 * <ul>
 * <li>It is not necessary to call beginTask() or done() on an instance of SubProgress.</li>
 * <li>SubProgress has a simpler syntax for creating nested monitors.</li>
 * <li>SubProgress is more efficient for deep recursion chains.</li>
 * <li>SubProgress has a setWorkRemining method that allows the remaining space on the monitor to be
 * redistributed without reporting any work.</li>
 * <li>SubProgress protects the caller from common progress reporting bugs in a called method. For example,
 * if a called method fails to call done() on the given monitor or fails to consume all the ticks on
 * the given monitor, the parent will correct the problem after the method returns.</li>
 * </ul>
 * <p></p>
 * <p><b>USAGE:</b></p>
 * 
 * <p>When implementing a method that accepts an IModelioProgress:</p>
 * <ul>
 * <li>At the start of your method, use <code>SubProgress.convert(...).</code> to convert the IModelioProgress
 * into a SubProgress. </li>
 * <li>Use <code>SubProgress.newChild(...)</code> whenever you need to call another method that
 * accepts an IModelioProgress.</li>
 * </ul>
 * <p></p>
 * <p><b>DEFAULT BEHAVIOR:</b></p>
 * 
 * <p>When writing JavaDoc for a method that accepts an IModelioProgress, you should assume the
 * following default behavior unless the method's JavaDoc says otherwise:</p>
 * <ul>
 * <li>It WILL call beginTask on the IModelioProgress.</li>
 * <li>It WILL NOT accept a null argument.</li>
 * <li>It WILL call done on the IModelioProgress.</li>
 * </ul>
 * <p></p>
 * <p><b>BEST PRACTISES:</b></p>
 * 
 * <p>We recommend that newly-written methods follow the given contract:</p>
 * <ul>
 * <li>It WILL call beginTask on the IModelioProgress.</li>
 * <li>It WILL accept a null argument, indicating that no progress should be reported and the operation cannot be cancelled.</li>
 * <li>It WILL NOT call done on the IModelioProgress, leaving this responsibility up to the caller.</li>
 * </ul>
 * <p>If you wish to follow these conventions, you may copy and paste the following text into your method's JavaDoc:</p>
 * 
 * <pre>@param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility
 * to call done() on the given monitor. Accepts <code>null</code>, indicating that no progress should be
 * reported and that the operation cannot be cancelled.</pre>
 * 
 * <p></p>
 * <p><b>Example: Recommended usage</b></p>
 * 
 * <p>This example demonstrates how the recommended usage of <code>SubProgress</code> makes it unnecessary to call
 * IModelioProgress.done() in most situations.</p>
 * 
 * <p>It is never necessary to call done() on a monitor obtained from <code>convert</code> or <code>progress.newChild()</code>.
 * In this example, there is no guarantee that <code>monitor</code> is an instance of <code>SubProgress</code>, making it
 * necessary to call <code>monitor.done()</code>. The JavaDoc contract makes this the responsibility of the caller.</p>
 * 
 * <pre>
 * // param monitor the progress monitor to use for reporting progress to the user. It is the caller's responsibility
 * //        to call done() on the given monitor. Accepts <code>null</code>, indicating that no progress should be
 * //        reported and that the operation cannot be cancelled.
 * //
 * void doSomething(IModelioProgress monitor) {
 * // Convert the given monitor into a progress instance
 * SubProgress progress = SubProgress.convert(monitor, 100);
 * 
 * // Use 30% of the progress to do some work
 * doSomeWork(progress.newChild(30));
 * 
 * // Advance the monitor by another 30%
 * progress.worked(30);
 * 
 * // Use the remaining 40% of the progress to do some more work
 * doSomeWork(progress.newChild(40));
 * }
 * </pre>
 * 
 * 
 * <p></p>
 * <p><b>Example: Default usage</b></p>
 * 
 * <p>You will often need to implement a method that does not explicitly stipulate that calling done() is the responsibility
 * of the caller. In this case, you should use the following pattern:</p>
 * 
 * <pre>
 * // param monitor the progress monitor to use for reporting progress to the user, or <code>null</code> indicating
 * //        that no progress should be reported and the operation cannot be cancelled.
 * //
 * void doSomething(IModelioProgress monitor) {
 * // Convert the given monitor into a progress instance
 * SubProgress progress = SubProgress.convert(monitor, 100);
 * try {
 * // Use 30% of the progress to do some work
 * doSomeWork(progress.newChild(30));
 * 
 * // Advance the monitor by another 30%
 * progress.worked(30);
 * 
 * // Use the remaining 40% of the progress to do some more work
 * doSomeWork(progress.newChild(40));
 * 
 * } finally {
 * if (monitor != null) {
 * monitor.done();
 * }
 * }
 * }
 * </pre>
 * 
 * <p></p>
 * <p><b>Example: Branches</b></p>
 * 
 * <p>This example demonstrates how to smoothly report progress in situations where some of the work is optional.</p>
 * 
 * <pre>
 * void doSomething(IModelioProgress monitor) {
 * SubProgress progress = SubProgress.convert(monitor, 100);
 * 
 * if (condition) {
 * // Use 50% of the progress to do some work
 * doSomeWork(progress.newChild(50));
 * }
 * 
 * // Don't report any work, but ensure that we have 50 ticks remaining on the progress monitor.
 * // If we already consumed 50 ticks in the above branch, this is a no-op. Otherwise, the remaining
 * // space in the monitor is redistributed into 50 ticks.
 * 
 * progress.setWorkRemaining(50);
 * 
 * // Use the remainder of the progress monitor to do the rest of the work
 * doSomeWork(progress.newChild(50));
 * }
 * </pre>
 * 
 * <p>Please beware of the following anti-pattern:</p>
 * 
 * <pre>
 * if (condition) {
 * // Use 50% of the progress to do some work
 * doSomeWork(progress.newChild(50));
 * } else {
 * // Bad: Causes the progress monitor to appear to start at 50%, wasting half of the
 * // space in the monitor.
 * progress.worked(50);
 * }
 * </pre>
 * 
 * 
 * <p></p>
 * <p><b>Example: Loops</b></p>
 * 
 * <p>This example demonstrates how to report progress in a loop.</p>
 * 
 * <pre>
 * void doSomething(IModelioProgress monitor, Collection someCollection) {
 * SubProgress progress = SubProgress.convert(monitor, 100);
 * 
 * // Create a new progress monitor that uses 70% of the total progress and will allocate one tick
 * // for each element of the given collection.
 * SubProgress loopProgress = progress.newChild(70).setWorkRemaining(someCollection.size());
 * 
 * for (Iterator iter = someCollection.iterator(); iter.hasNext();) {
 * Object next = iter.next();
 * 
 * doWorkOnElement(next, loopProgress.newChild(1));
 * }
 * 
 * // Use the remaining 30% of the progress monitor to do some work outside the loop
 * doSomeWork(progress.newChild(30));
 * }
 * </pre>
 * 
 * 
 * <p></p>
 * <p><b>Example: Infinite progress</b></p>
 * 
 * <p>This example demonstrates how to report logarithmic progress in situations where the number of ticks
 * cannot be easily computed in advance.</p>
 * 
 * <pre>
 * void doSomething(IModelioProgress monitor, LinkedListNode node) {
 * SubProgress progress = SubProgress.convert(monitor);
 * 
 * while (node != null) {
 * // Regardless of the amount of progress reported so far,
 * // use 0.01% of the space remaining in the monitor to process the next node.
 * progress.setWorkRemaining(10000);
 * 
 * doWorkOnElement(node, progress.newChild(1));
 * 
 * node = node.next;
 * }
 * }
 * </pre>
 * 
 * <p>
 * This class can be used without OSGi running.
 * </p>
 * 
 * @since org.eclipse.equinox.common 3.3
 */
@objid ("c98f517a-a5a3-11e1-aa98-001ec947ccaf")
public final class SubProgress implements IModelioProgress {
    /**
     * Minimum number of ticks to allocate when calling beginTask on an unknown IModelioProgress.
     * Pick a number that is big enough such that, no matter where progress is being displayed,
     * the user would be unlikely to notice if progress were to be reported with higher accuracy.
     */
    @objid ("c98f517c-a5a3-11e1-aa98-001ec947ccaf")
    private static final int MINIMUM_RESOLUTION = 1000;

    /**
     * Total number of ticks that this progress monitor is permitted to consume
     * from the root.
     */
    @objid ("c98f519c-a5a3-11e1-aa98-001ec947ccaf")
    private int totalParent;

    /**
     * Number of ticks that this progress monitor has already reported in the root.
     */
    @objid ("c98f519e-a5a3-11e1-aa98-001ec947ccaf")
    private int usedForParent = 0;

    /**
     * Number of ticks that have been consumed by this instance's children.
     */
    @objid ("c98f51a0-a5a3-11e1-aa98-001ec947ccaf")
    private double usedForChildren = 0.0;

    /**
     * Number of ticks allocated for this instance's children. This is the total number
     * of ticks that may be passed into worked(int) or newChild(int).
     */
    @objid ("c98f51a2-a5a3-11e1-aa98-001ec947ccaf")
    private int totalForChildren;

    /**
     * A bitwise combination of the SUPPRESS_* flags.
     */
    @objid ("c98f51a9-a5a3-11e1-aa98-001ec947ccaf")
    private final int flags;

    /**
     * May be passed as a flag to newChild. Indicates that the calls
     * to subTask on the child should be ignored. Without this flag,
     * calling subTask on the child will result in a call to subTask
     * on its parent.
     */
    @objid ("c98f51ac-a5a3-11e1-aa98-001ec947ccaf")
    public static final int SUPPRESS_SUBTASK = 0x0001;

    /**
     * May be passed as a flag to newChild. Indicates that strings
     * passed into beginTask should be ignored. If this flag is
     * specified, then the progress monitor instance will accept null
     * as the first argument to beginTask. Without this flag, any
     * string passed to beginTask will result in a call to
     * setTaskName on the parent.
     */
    @objid ("c98f51af-a5a3-11e1-aa98-001ec947ccaf")
    public static final int SUPPRESS_BEGINTASK = 0x0002;

    /**
     * May be passed as a flag to newChild. Indicates that strings
     * passed into setTaskName should be ignored. If this string
     * is omitted, then a call to setTaskName on the child will
     * result in a call to setTaskName on the parent.
     */
    @objid ("c98f51b2-a5a3-11e1-aa98-001ec947ccaf")
    public static final int SUPPRESS_SETTASKNAME = 0x0004;

    /**
     * May be passed as a flag to newChild. Indicates that strings
     * passed to setTaskName, subTask, and beginTask should all be ignored.
     */
    @objid ("c991b3c0-a5a3-11e1-aa98-001ec947ccaf")
    public static final int SUPPRESS_ALL_LABELS = SUPPRESS_SETTASKNAME | SUPPRESS_BEGINTASK | SUPPRESS_SUBTASK;

    /**
     * May be passed as a flag to newChild. Indicates that strings
     * passed to setTaskName, subTask, and beginTask should all be propagated
     * to the parent.
     */
    @objid ("c991b3c3-a5a3-11e1-aa98-001ec947ccaf")
    public static final int SUPPRESS_NONE = 0;

    /**
     * Children created by newChild will be completed automatically the next time
     * the parent progress monitor is touched. This points to the last incomplete child
     * created with newChild.
     */
    @objid ("c98f51a4-a5a3-11e1-aa98-001ec947ccaf")
    private IModelioProgress lastSubMonitor = null;

    @objid ("efd4ea26-59a3-4c58-8090-4796532cdd6e")
    private ChildSupplier lastChildSupplier;

    /**
     * Used to communicate with the root of this progress monitor tree
     */
    @objid ("c98f51a6-a5a3-11e1-aa98-001ec947ccaf")
    private final RootInfo root;

    /**
     * Creates a new SubProgress that will report its progress via
     * the given RootInfo.
     * @param rootInfo the root of this progress monitor tree
     * @param totalWork total work to perform on the given progress monitor
     * @param availableToChildren number of ticks allocated for this instance's children
     * @param flags a bitwise combination of the SUPPRESS_* constants
     */
    @objid ("c991b3c6-a5a3-11e1-aa98-001ec947ccaf")
    private  SubProgress(final RootInfo rootInfo, final int totalWork, final int availableToChildren, final int flags) {
        this.root = rootInfo;
        this.totalParent = (totalWork > 0) ? totalWork : 0;
        this.totalForChildren = availableToChildren;
        this.flags = flags;
        
    }

    /**
     * <p>Converts an unknown (possibly null) IModelioProgress into a SubProgress. It is
     * not necessary to call done() on the result, but the caller is responsible for calling
     * done() on the argument. Calls beginTask on the argument.</p>
     * 
     * <p>This method should generally be called at the beginning of a method that accepts
     * an IModelioProgress in order to convert the IModelioProgress into a SubProgress.</p>
     * @param monitor monitor to convert to a SubProgress instance or null. Treats null
     * as a new instance of <code>NullProgressMonitor</code>.
     * @return a SubProgress instance that adapts the argument
     */
    @objid ("c991b3cd-a5a3-11e1-aa98-001ec947ccaf")
    public static SubProgress convert(final IModelioProgress monitor) {
        return convert(monitor, "", 0); //$NON-NLS-1$
    }

    /**
     * <p>Converts an unknown (possibly null) IModelioProgress into a SubProgress allocated
     * with the given number of ticks. It is not necessary to call done() on the result,
     * but the caller is responsible for calling done() on the argument. Calls beginTask
     * on the argument.</p>
     * 
     * <p>This method should generally be called at the beginning of a method that accepts
     * an IModelioProgress in order to convert the IModelioProgress into a SubProgress.</p>
     * @param monitor monitor to convert to a SubProgress instance or null. Treats null
     * as a new instance of <code>NullProgressMonitor</code>.
     * @param work number of ticks that will be available in the resulting monitor
     * @return a SubProgress instance that adapts the argument
     */
    @objid ("c991b3d3-a5a3-11e1-aa98-001ec947ccaf")
    public static SubProgress convert(final IModelioProgress monitor, final int work) {
        return convert(monitor, "", work); //$NON-NLS-1$
    }

    /**
     * <p>Converts an unknown (possibly null) IModelioProgress into a SubProgress allocated
     * with the given number of ticks. It is not necessary to call done() on the result,
     * but the caller is responsible for calling done() on the argument. Calls beginTask
     * on the argument.</p>
     * 
     * <p>This method should generally be called at the beginning of a method that accepts
     * an IModelioProgress in order to convert the IModelioProgress into a SubProgress.</p>
     * @param initialMonitor to convert into a SubProgress instance or null. If given a null argument,
     * the resulting SubProgress will not report its progress anywhere.
     * @param taskName user readable name to pass to monitor.beginTask. Never null.
     * @param work initial number of ticks to allocate for children of the SubProgress
     * @return a new SubProgress instance that is a child of the given monitor
     */
    @objid ("c991b3da-a5a3-11e1-aa98-001ec947ccaf")
    public static SubProgress convert(IModelioProgress initialMonitor, final String taskName, final int work) {
        IModelioProgress monitor = initialMonitor;
        if (monitor == null) {
            monitor = new NullProgress();
        }
        
        // Optimization: if the given monitor already a SubProgress, no conversion is necessary
        if (monitor instanceof SubProgress) {
            monitor.beginTask(taskName, work);
            return (SubProgress) monitor;
        }
        
        monitor.beginTask(taskName, MINIMUM_RESOLUTION);
        return new SubProgress(new RootInfo(monitor), MINIMUM_RESOLUTION, work, SUPPRESS_NONE);
    }

    /**
     * <p>Sets the work remaining for this SubProgress instance. This is the total number
     * of ticks that may be reported by all subsequent calls to worked(int), newChild(int), etc.
     * This may be called many times for the same SubProgress instance. When this method
     * is called, the remaining space on the progress monitor is redistributed into the given
     * number of ticks.</p>
     * 
     * <p>It doesn't matter how much progress has already been reported with this SubProgress
     * instance. If you call setWorkRemaining(100), you will be able to report 100 more ticks of
     * work before the progress meter reaches 100%.</p>
     * @param initialWorkRemaining total number of remaining ticks
     * @return the receiver
     */
    @objid ("c991b3e2-a5a3-11e1-aa98-001ec947ccaf")
    public SubProgress setWorkRemaining(int initialWorkRemaining) {
        // Ensure we don't try to allocate negative ticks
        int workRemaining = Math.max(0, initialWorkRemaining);
        
        // Ensure we don't cause division by zero
        if (this.totalForChildren > 0 && this.totalParent > this.usedForParent) {
            // Note: We want the following value to remain invariant after this method returns
            double remainForParent = this.totalParent * (1.0d - (this.usedForChildren / this.totalForChildren));
            this.usedForChildren = (workRemaining * (1.0d - remainForParent / (this.totalParent - this.usedForParent)));
        } else {
            this.usedForChildren = 0.0d;
        }
        
        this.totalParent = this.totalParent - this.usedForParent;
        this.usedForParent = 0;
        this.totalForChildren = workRemaining;
        return this;
    }

    /**
     * Consumes the given number of child ticks, given as a double. Must only
     * be called if the monitor is in floating-point mode.
     * @param ticks the number of ticks to consume
     * @return ticks the number of ticks to be consumed from parent
     */
    @objid ("c991b3e8-a5a3-11e1-aa98-001ec947ccaf")
    private int consume(final double ticks) {
        if (this.totalParent == 0 || this.totalForChildren == 0) {
            return 0;
        }
        
        this.usedForChildren += ticks;
        
        if (this.usedForChildren > this.totalForChildren) {
            this.usedForChildren = this.totalForChildren;
        } else if (this.usedForChildren < 0.0) {
            this.usedForChildren = 0.0;
        }
        
        int parentPosition = (int) (this.totalParent * this.usedForChildren / this.totalForChildren);
        int delta = parentPosition - this.usedForParent;
        
        this.usedForParent = parentPosition;
        return delta;
    }

    @objid ("c991b3ee-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public boolean isCanceled() {
        return this.root.isCanceled();
    }

    @objid ("c991b3f3-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public void setTaskName(final String name) {
        if ((this.flags & SUPPRESS_SETTASKNAME) == 0) {
            this.root.setTaskName(name);
        }
        
    }

    /**
     * Starts a new main task.
     * <p>
     * The string argument is ignored if and only if the {@link #SUPPRESS_BEGINTASK}
     * flag has been set on this SubProgressinstance.
     * 
     * <p>This method is equivalent calling setWorkRemaining(...) on the receiver. Unless
     * the {@link #SUPPRESS_BEGINTASK} flag is set, this will also be equivalent to calling
     * {@link #setTaskName(String)} on the parent.</p>
     * @see IModelioProgress#beginTask(java.lang.String, int)
     * @param name new main task name
     * @param totalWork number of ticks to allocate
     */
    @objid ("c991b3f7-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public void beginTask(final String name, final int totalWork) {
        if ((this.flags & SUPPRESS_BEGINTASK) == 0 && name != null) {
            this.root.setTaskName(name);
        }
        setWorkRemaining(totalWork);
        
    }

    /* (non-Javadoc)
         * @see org.eclipse.core.runtime.IModelioProgress#done()
         */
    @objid ("c991b3fc-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public void done() {
        cleanupActiveChild();
        int delta = this.totalParent - this.usedForParent;
        if (delta > 0) {
            this.root.worked(delta);
        }
        
        this.totalParent = 0;
        this.usedForParent = 0;
        this.totalForChildren = 0;
        this.usedForChildren = 0.0d;
        
    }

    /* (non-Javadoc)
         * @see org.eclipse.core.runtime.IModelioProgress#internalWorked(double)
         */
    @objid ("c991b3ff-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public void internalWorked(final double work) {
        cleanupActiveChild();
        
        int delta = consume((work > 0.0d) ? work : 0.0d);
        if (delta != 0) {
            this.root.worked(delta);
        }
        
    }

    /* (non-Javadoc)
         * @see org.eclipse.core.runtime.IModelioProgress#subTask(java.lang.String)
         */
    @objid ("c991b403-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public void subTask(final String name) {
        if ((this.flags & SUPPRESS_SUBTASK) == 0) {
            this.root.subTask(name);
        }
        
    }

    /* (non-Javadoc)
         * @see org.eclipse.core.runtime.IModelioProgress#worked(int)
         */
    @objid ("c991b407-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public void worked(final int work) {
        internalWorked(work);
    }

    /* (non-Javadoc)
         * @see org.eclipse.core.runtime.IModelioProgress#setCanceled(boolean)
         */
    @objid ("c991b40b-a5a3-11e1-aa98-001ec947ccaf")
    @Override
    public void setCanceled(final boolean b) {
        this.root.setCanceled(b);
    }

    /**
     * <p>Creates a sub progress monitor that will consume the given number of ticks from the
     * receiver. It is not necessary to call <code>beginTask</code> or <code>done</code> on the
     * result. However, the resulting progress monitor will not report any work after the first
     * call to done() or before ticks are allocated. Ticks may be allocated by calling beginTask
     * or setWorkRemaining.</p>
     * 
     * <p>Each SubProgress only has one active child at a time. Each time newChild() is called, the
     * result becomes the new active child and any unused progress from the previously-active child is
     * consumed.</p>
     * 
     * <p>This is property makes it unnecessary to call done() on a SubProgress instance, since child
     * monitors are automatically cleaned up the next time the parent is touched.</p>
     * 
     * <code><pre>
     * ////////////////////////////////////////////////////////////////////////////
     * // Example 1: Typical usage of newChild
     * void myMethod(IModelioProgress parent) {
     * SubProgress progress = SubProgress.convert(parent, 100);
     * doSomething(progress.newChild(50));
     * doSomethingElse(progress.newChild(50));
     * }
     * 
     * ////////////////////////////////////////////////////////////////////////////
     * // Example 2: Demonstrates the function of active children. Creating children
     * // is sufficient to smoothly report progress, even if worked(...) and done()
     * // are never called.
     * void myMethod(IModelioProgress parent) {
     * SubProgress progress = SubProgress.convert(parent, 100);
     * 
     * for (int i = 0; i < 100; i++) {
     * // Creating the next child monitor will clean up the previous one,
     * // causing progress to be reported smoothly even if we don't do anything
     * // with the monitors we create
     * progress.newChild(1);
     * }
     * }
     * 
     * ////////////////////////////////////////////////////////////////////////////
     * // Example 3: Demonstrates a common anti-pattern
     * void wrongMethod(IModelioProgress parent) {
     * SubProgress progress = SubProgress.convert(parent, 100);
     * 
     * // WRONG WAY: Won't have the intended effect, as only one of these progress
     * // monitors may be active at a time and the other will report no progress.
     * callMethod(progress.newChild(50), computeValue(progress.newChild(50)));
     * }
     * 
     * void rightMethod(IModelioProgress parent) {
     * SubProgress progress = SubProgress.convert(parent, 100);
     * 
     * // RIGHT WAY: Break up method calls so that only one SubProgress is in use at a time.
     * Object someValue = computeValue(progress.newChild(50));
     * callMethod(progress.newChild(50), someValue);
     * }
     * </pre></code>
     * @param totalWork number of ticks to consume from the receiver
     * @return new sub progress monitor that may be used in place of a new SubProgress
     */
    @objid ("c991b40f-a5a3-11e1-aa98-001ec947ccaf")
    public SubProgress newChild(final int totalWork) {
        return newChild(totalWork, SUPPRESS_BEGINTASK);
    }

    /**
     * <p>Creates a sub progress monitor that will consume the given number of ticks from the
     * receiver. It is not necessary to call <code>beginTask</code> or <code>done</code> on the
     * result. However, the resulting progress monitor will not report any work after the first
     * call to done() or before ticks are allocated. Ticks may be allocated by calling beginTask
     * or setWorkRemaining.</p>
     * 
     * <p>Each SubProgress only has one active child at a time. Each time newChild() is called, the
     * result becomes the new active child and any unused progress from the previously-active child is
     * consumed.</p>
     * 
     * <p>This is property makes it unnecessary to call done() on a SubProgress instance, since child
     * monitors are automatically cleaned up the next time the parent is touched.</p>
     * 
     * <code><pre>
     * ////////////////////////////////////////////////////////////////////////////
     * // Example 1: Typical usage of newChild
     * void myMethod(IModelioProgress parent) {
     * SubProgress progress = SubProgress.convert(parent, 100);
     * doSomething(progress.newChild(50));
     * doSomethingElse(progress.newChild(50));
     * }
     * 
     * ////////////////////////////////////////////////////////////////////////////
     * // Example 2: Demonstrates the function of active children. Creating children
     * // is sufficient to smoothly report progress, even if worked(...) and done()
     * // are never called.
     * void myMethod(IModelioProgress parent) {
     * SubProgress progress = SubProgress.convert(parent, 100);
     * 
     * for (int i = 0; i < 100; i++) {
     * // Creating the next child monitor will clean up the previous one,
     * // causing progress to be reported smoothly even if we don't do anything
     * // with the monitors we create
     * progress.newChild(1);
     * }
     * }
     * 
     * ////////////////////////////////////////////////////////////////////////////
     * // Example 3: Demonstrates a common anti-pattern
     * void wrongMethod(IModelioProgress parent) {
     * SubProgress progress = SubProgress.convert(parent, 100);
     * 
     * // WRONG WAY: Won't have the intended effect, as only one of these progress
     * // monitors may be active at a time and the other will report no progress.
     * callMethod(progress.newChild(50), computeValue(progress.newChild(50)));
     * }
     * 
     * void rightMethod(IModelioProgress parent) {
     * SubProgress progress = SubProgress.convert(parent, 100);
     * 
     * // RIGHT WAY: Break up method calls so that only one SubProgress is in use at a time.
     * Object someValue = computeValue(progress.newChild(50));
     * callMethod(progress.newChild(50), someValue);
     * }
     * </pre></code>
     * @param totalWork number of ticks to consume from the receiver
     * @return new sub progress monitor that may be used in place of a new SubProgress
     */
    @objid ("c991b415-a5a3-11e1-aa98-001ec947ccaf")
    public SubProgress newChild(final int totalWork, final int suppressFlags) {
        double totalWorkDouble = (totalWork > 0) ? totalWork : 0.0d;
        totalWorkDouble = Math.min(totalWorkDouble, this.totalForChildren - this.usedForChildren);
        cleanupActiveChild();
        
        // Compute the flags for the child. We want the net effect to be as though the child is
        // delegating to its parent, even though it is actually talking directly to the root.
        // This means that we need to compute the flags such that - even if a label isn't 
        // suppressed by the child - if that same label would have been suppressed when the
        // child delegated to its parent, the child must explicitly suppress the label. 
        int childFlags = SUPPRESS_NONE;
        
        if ((this.flags & SUPPRESS_SETTASKNAME) != 0) {
            // If the parent was ignoring labels passed to setTaskName, then the child will ignore
            // labels passed to either beginTask or setTaskName - since both delegate to setTaskName
            // on the parent
            childFlags |= SUPPRESS_SETTASKNAME | SUPPRESS_BEGINTASK;
        }
        
        if ((this.flags & SUPPRESS_SUBTASK) != 0) {
            // If the parent was suppressing labels passed to subTask, so will the child.
            childFlags |= SUPPRESS_SUBTASK;
        }
        
        // Note: the SUPPRESS_BEGINTASK flag does not affect the child since there
        // is no method on the child that would delegate to beginTask on the parent.
        childFlags |= suppressFlags;
        
        SubProgress result = new SubProgress(this.root, consume(totalWorkDouble), (int) totalWorkDouble, childFlags);
        this.lastSubMonitor = result;
        return result;
    }

    /**
     * Create an optional sub progress monitor.
     * <p>
     * Calling {@link Supplier#get()} on the returned supplier has
     * then the same effect as calling {@link SubProgress#newChild(int, int)}.
     * <p>
     * This method allows giving to another process the optional possibility to use a progress monitor.
     * If the user choose to not use the progress supplier no work ticks will be allocated.
     * <p>
     * The caller is advised to use {@link SubProgress#setWorkRemaining(int)} after having
     * passed the supplier to the process that may have or not used it.
     * <p>
     * The returned supplier may be used only once is valid until this sub progress finishes
     * or another child progress is created.
     * @param totalWork number of ticks to consume from the receiver
     * @param suppressFlags a bitwise combination of the SUPPRESS_* constants
     * @return an optional sub progress monitor.
     * @since Modelio 3.6
     */
    @objid ("1468d7da-2ca2-4776-a2a1-b747f79cbe72")
    public Supplier<SubProgress> newChildSupplier(final int totalWork, int suppressFlags) {
        ChildSupplier childSupplier = new ChildSupplier(this, totalWork, suppressFlags);
        if (this.lastChildSupplier != null) {
            this.lastChildSupplier.invalidate();
        }
        this.lastChildSupplier = childSupplier;
        return childSupplier;
    }

    /**
     * Create an optional sub progress monitor.
     * <p>
     * Calling {@link Supplier#get()} on the returned supplier has
     * then the same effect as calling {@link SubProgress#newChild(int, int)}.
     * <p>
     * This method allows giving to another process the optional possibility to use a progress monitor.
     * If the user choose to not use the progress supplier no work ticks will be allocated.
     * <p>
     * The caller is advised to use {@link SubProgress#setWorkRemaining(int)} after having
     * passed the supplier to the process that may have or not used it.
     * <p>
     * The returned supplier may be used only once is valid until this sub progress finishes
     * or another child progress is created.
     * @param totalWork number of ticks to consume from the receiver
     * @return an optional sub progress monitor.
     * @since Modelio 3.6
     */
    @objid ("e9095ef7-e2ce-4a80-8e03-30da9916d13f")
    public Supplier<SubProgress> newChildSupplier(final int totalWork) {
        return newChildSupplier(totalWork, SUPPRESS_BEGINTASK);
    }

    @objid ("c991b41c-a5a3-11e1-aa98-001ec947ccaf")
    private void cleanupActiveChild() {
        if (this.lastChildSupplier != null) {
            this.lastChildSupplier.invalidate();
            this.lastChildSupplier = null;
        }
        if (this.lastSubMonitor != null) {
            IModelioProgress child = this.lastSubMonitor;
            this.lastSubMonitor = null;
            child.done();
        }
        
    }

    /**
     * The RootInfo holds information about the root progress monitor. A SubProgress and
     * its active descendents share the same RootInfo.
     */
    @objid ("c98f517f-a5a3-11e1-aa98-001ec947ccaf")
    private static final class RootInfo {
        /**
         * Remembers the last task name. Prevents us from setting the same task name multiple
         * times in a row.
         */
        @objid ("d5af77e5-a5a3-11e1-aa98-001ec947ccaf")
        private String taskName = null;

        /**
         * Remembers the last subtask name. Prevents the SubProgress from setting the same
         * subtask string more than once in a row.
         */
        @objid ("d5af77ea-a5a3-11e1-aa98-001ec947ccaf")
        private String subTask = null;

        @objid ("c98f5182-a5a3-11e1-aa98-001ec947ccaf")
        private final IModelioProgress root;

        /**
         * Creates a RootInfo struct that delegates to the given progress
         * monitor.
         * @param root progress monitor to delegate to
         */
        @objid ("c98f5188-a5a3-11e1-aa98-001ec947ccaf")
        public  RootInfo(final IModelioProgress root) {
            this.root = root;
        }

        @objid ("c98f518c-a5a3-11e1-aa98-001ec947ccaf")
        public boolean isCanceled() {
            return this.root.isCanceled();
        }

        @objid ("c98f5190-a5a3-11e1-aa98-001ec947ccaf")
        public void setCanceled(final boolean value) {
            this.root.setCanceled(value);
        }

        @objid ("c98f5193-a5a3-11e1-aa98-001ec947ccaf")
        public void setTaskName(final String taskName) {
            if (Objects.equals(taskName, this.taskName)) {
                return;
            }
            this.taskName = taskName;
            this.root.setTaskName(taskName);
            
        }

        @objid ("c98f5196-a5a3-11e1-aa98-001ec947ccaf")
        public void subTask(final String name) {
            if (Objects.equals(this.subTask, name)) {
                return;
            }
            
            this.subTask = name;
            this.root.subTask(name);
            
        }

        @objid ("c98f5199-a5a3-11e1-aa98-001ec947ccaf")
        public void worked(final int i) {
            this.root.worked(i);
        }

    }

    /**
     * @author cma
     * @since Modelio 3.6
     */
    @objid ("04c8f611-a387-4327-89d3-564e63f622e8")
    private static class ChildSupplier implements Supplier<SubProgress> {
        @objid ("380078a0-95ce-40f4-be9d-745b04e5ab9c")
        private final int totalWork;

        @objid ("0934d56e-44d8-4cbf-8b01-a54a6b47880d")
        private final int suppressFlags;

        @objid ("b9aea6aa-3c2f-42f1-9e80-e2ed4aef0f8e")
        private SubProgress parent;

        @objid ("e29339ee-af78-4ed3-b25c-7bfe880177ad")
        public  ChildSupplier(SubProgress parent, int totalWork, int suppressFlags) {
            super();
            this.totalWork = totalWork;
            this.suppressFlags = suppressFlags;
            this.parent = parent;
            
        }

        @objid ("f88d7e7e-1cec-4e4a-a6cc-bd8606b5ed54")
        @Override
        public SubProgress get() {
            if (this.parent == null) {
                throw new IllegalStateException("This supplier expired.");
            }
            // This automatically invalidates this supplier.
            return this.parent.newChild(this.totalWork, this.suppressFlags);
        }

        @objid ("a789d218-f596-4592-aaa0-b7fe718351ce")
        public void invalidate() {
            this.parent = null;
        }

    }

}
