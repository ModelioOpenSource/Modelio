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
package com.sun.star.comp.beans;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import com.modeliosoft.modelio.javadesigner.annotations.objid;
import com.sun.star.awt.XCallback;
import com.sun.star.awt.XRequestCallback;
import com.sun.star.lang.XMultiComponentFactory;
import com.sun.star.uno.Any;
import com.sun.star.uno.UnoRuntime;
import com.sun.star.uno.XComponentContext;
import org.eclipse.swt.widgets.Display;

/**
 * Used to call a task in the OpenOffice main thread.
 * <p>
 * 
 * Table of OpenOffice callbacks requirements:
 * 
 * <pre>
 * Method                             Needs   Needs      Listener
 * Callback Listener
 * ---------------------------------------------------------------------
 * XComponentLoader from Desktop
 * .loadComponentFromUrl            Y       N  doc.XEventListener
 * ?? OnLoadFinished
 * ?? OnLoadDone
 * XDesktop.terminate                   Y       O  XTerminateListener
 * XComponentLoader from Frame
 * .loadComponentFromUrl            Y       N  doc.XEventListener
 * ?? OnLoadFinished
 * ?? OnLoadClosed
 * XDocumentInsertable
 * .insertDocumentFromURL           Y       N  doc.XEventListener
 * ?? OnInsertDone
 * ?? OnLoadDone
 * ?? OnLoadFinished
 * XDocumentIndex.update                Y       ?  ???
 * XRefreshable.refresh                 Y       Y  XRefreshListener
 * XPrintable.print (with Wait)         Y       N  XPrintListener
 * XStorable.storeToURL                 Y       Y  doc.XEventListener
 * ?? OnSaveDone
 * ?? OnSaveAsDone
 * ?? OnSaveFinished
 * 
 * XCloseable.close                     ?       O  lang.XEventListener
 * XComponent.dispose                   ?       O  lang.XEventListener
 * 
 * UnoRuntime.queryInterface            N       N
 * XComponent.addEventListener          N       N
 * XComponentContext.getServiceManager  N       N
 * XController.getFrame                 N       N
 * XDesktop.addTerminateListener        N       N
 * XDocumentIndexesSupplier
 * .getDocumentIndexes              N       N
 * XModel.getCurrentController          N       N
 * XModifiable.setModified              N       N
 * XMultiComponentFactory
 * .createInstanceWithContext       N       N
 * XPrintable.setPrinter                N       N
 * XPrintJobBroadcaster
 * .addPrintJobListener             N       N
 * XPrintJobBroadcaster
 * .removePrintJobListener          N       N
 * XRefreshable.addRefreshListener      N       N
 * XRefreshable.removeRefreshListener   N       N
 * XServiceInfo.supportsService         N       N
 * XTextDocument.getText                N       N
 * XText.createTextCursor               N       N
 * </pre>
 * 
 * 
 * @author cma
 * @param <T>
 * the type of the result returned by the task.
 * @see Callable
 * @see <a href="http://wiki.services.openoffice.org/wiki/Framework/Article/Asynchronous_Callback_Service">OpenOffice Asynchronous callback service</a>
 * @see <a href="http://www.mail-archive.com/dev@api.openoffice.org/msg08705.html">[api-dev] Do all calls into Office require the XCallback</a>
 * @see <a href="http://openoffice.2283327.n4.nabble.com/Xstorable-storeToUrl-locks-soffice-bin-td2770915.html">Xstorable.storeToUrl locks soffice.bin</a>
 * @see <a href="https://svn.apache.org/repos/asf/incubator/ooo/trunk/main/qadevOOo/tests/java/ifc/awt/_XMessageBoxFactory.java">XMessageBoxFactory.java</a>
 * @see <a href="https://svn.apache.org/repos/asf/incubator/ooo/trunk/main/swext/mediawiki/src/com/sun/star/wiki/MainThreadDialogExecutor.java">MainThreadDialogExecutor.java</a>
 */
@objid ("22bbcfba-e9f3-4940-b40e-849da07ef042")
public final class UnoCaller {
    @objid ("f38b3a77-b9b4-4a2f-8910-0ce893dfaa40")
    private final XComponentContext xContext;

    @objid ("8fd2d9b3-30f7-41f4-99c6-a071506fa355")
    private volatile boolean disposed;

    /**
     * Service used to execute OpenOffice UNO calls in another thread.
     * <p>
     * Necessary because it seems OpenOffice fails answering calls launched from the SWT thread.
     */
    @objid ("37255090-3cf9-46ee-b784-11f0f9826f54")
    private static final ExecutorService unoThreadExecutor = ForkJoinPool.commonPool(); // Executors.newCachedThreadPool();

    /**
     * Call synchronously an operation in the OpenOffice main thread.
     * <p>
     * If called from the SWT Display thread, run an inner event loop while waiting.
     * @throws com.sun.star.uno.RuntimeException if the task couldn't be scheduled.
     * @param r the task to call
     * @return the result of the task.
     * @throws InvocationTargetException if the task thrown an exception, it is encapsulated in this exception.
     */
    @objid ("4053a2ed-cbf9-466a-abc7-7db2852d6c36")
    public <T> T call(final Callable<T> r) throws InvocationTargetException, com.sun.star.uno.RuntimeException {
        return call(r, true);
    }

    /**
     * Read and process SWT event loop events until there is none left.
     */
    @objid ("2b49fbf8-7666-44e1-b6b9-e9edb007f66e")
    private static void refreshDisplay() {
        if (Display.getCurrent() != null) {
            while (Display.getCurrent().readAndDispatch()) {
            }
        }
        
    }

    /**
     * Call synchronously the given callable in a background thread: neither the SWT thread nor the LibreOffice main thread.
     * <p>
     * Should be called from the SWT thread. in this case the GUI will refresh while waiting for the operation to finish.
     * <p>
     * Necessary because it seems OpenOffice fails answering calls launched from the SWT thread on Windows OS.
     * It also seems that the SWT thread must be able to answer windows event.
     * @param t the operation to call. The operation may return an IOException if it fails.
     * @throws IOException in case of failure.
     */
    @objid ("5887bbf4-d274-48f7-8c9d-da98cb2bb8d5")
    public static void callOtherThread(final Callable<IOException> t) throws IOException {
        // Calls must be done in a thread INDEPENDENT from the SWT thread.
        
        // Launch the call
        Future<IOException> res = UnoCaller.unoThreadExecutor.submit(t);
        
        try {
            // Wait while refreshing GUI if in GUI thread
            final Display display = Display.getCurrent();
        
            if (display != null) {
                while (!res.isDone()) {
                    if (!display.readAndDispatch()) {
                        Thread.sleep(10);
                    }
                }
            }
        
            // Process the result
            IOException error = res.get();
            if (error != null) {
                throw error;
            }
        
        } catch (InterruptedException e) {
            throw new IOException(e.getLocalizedMessage(), e);
        } catch (ExecutionException e) {
            throw new IOException(e.getCause().getLocalizedMessage(), e);
        }
        
    }

    /**
     * Call synchronously an operation in the OpenOffice main thread.
     * <p>
     * If called from the SWT Display thread, run an inner event loop while waiting.
     * @throws com.sun.star.uno.RuntimeException if the task couldn't be scheduled.
     * @param r the task to call
     * @param refreshDisplay if <code>true</code> and called from the SWT Display thread, run an inner event loop while waiting.
     * @return the result of the task.
     * @throws InvocationTargetException if the task thrown an exception, it is encapsulated in this exception.
     */
    @objid ("7e9f7fb6-e456-4870-bd73-a1fedc0a3344")
    public <T> T call(final Callable<T> r, boolean refreshDisplay) throws InvocationTargetException, com.sun.star.uno.RuntimeException {
        if (this.disposed) {
            throw new IllegalStateException("Disposed");
        }
        
        XMultiComponentFactory xFactory = this.xContext.getServiceManager();
        
        if (xFactory == null) {
            throw new com.sun.star.uno.RuntimeException("Cannot get XMultiComponentFactory from the XComponentContext.");
        }
        
        UnoCallback<T> aExecutor = new UnoCallback<>(r);
        try {
            XRequestCallback xRequest = UnoRuntime.queryInterface(XRequestCallback.class,
                    xFactory.createInstanceWithContext("com.sun.star.awt.AsyncCallback", this.xContext));
            if (xRequest != null) {
                xRequest.addCallback(aExecutor, Any.VOID);
                do {
                    if (refreshDisplay) {
                        refreshDisplay();
                    }
                    Thread.yield();
                } while (!aExecutor.res.isDone() && !this.disposed);
        
                if (!aExecutor.res.isDone()) {
                    aExecutor.res.cancel(true);
                }
            }
        } catch (com.sun.star.uno.Exception e) {
            throw (com.sun.star.uno.RuntimeException) new com.sun.star.uno.RuntimeException().initCause(e);
        }
        
        try {
            return aExecutor.res.get();
        } catch (InterruptedException e) {
            throw new InvocationTargetException(e);
        } catch (ExecutionException e) {
            throw new InvocationTargetException(e);
        }
        
    }

    /**
     * @param xContext the OpenOffice context.
     */
    @objid ("4e31db6c-d804-42b1-a2c9-455a23c01dad")
    public  UnoCaller(XComponentContext xContext) {
        this.xContext = xContext;
    }

    /**
     * Stops the service
     */
    @objid ("4ae95586-7fed-4f9e-b179-b2a7f029e697")
    public void setDisposed() {
        this.disposed = true;
    }

    /**
     * @return whether the service is disposed.
     */
    @objid ("ffbd4aa7-57d1-4db1-a0fa-d4402db18249")
    public boolean isDisposed() {
        return this.disposed;
    }

    /**
     * Call asynchronously an operation in the OpenOffice main thread.
     * <p>
     * @throws com.sun.star.uno.RuntimeException if the task couldn't be scheduled.
     * @param r the task to submit
     * @return the future result of the task.
     */
    @objid ("46cae9d8-b418-45bf-97af-6f553a4c9ab3")
    public <T> CompletableFuture<T> callAsync(final Callable<T> r) throws com.sun.star.uno.RuntimeException {
        if (r == null) {
            return CompletableFuture.completedFuture(null);
        }
        
        if (this.isDisposed()) {
            throw new IllegalStateException("disposed");
        }
        
        XMultiComponentFactory xFactory = this.xContext.getServiceManager();
        if (xFactory == null) {
            throw new com.sun.star.uno.RuntimeException("Cannot get XMultiComponentFactory from the XComponentContext.");
        }
        
        UnoCallback<T> aExecutor = new UnoCallback<>(r);
        
        try {
            XRequestCallback xRequest = UnoRuntime.queryInterface(XRequestCallback.class,
                    xFactory.createInstanceWithContext("com.sun.star.awt.AsyncCallback", this.xContext));
            if (xRequest != null) {
                xRequest.addCallback(aExecutor, Any.VOID);
                return aExecutor.res;
            }
        } catch (com.sun.star.uno.Exception e) {
            throw (com.sun.star.uno.RuntimeException) new com.sun.star.uno.RuntimeException().initCause(e);
        }
        return aExecutor.res;
    }

    @objid ("5e6c9075-2b81-4a1d-bfb3-8dd9856bd8b2")
    private static final class UnoCallback<T> implements XCallback {
        @objid ("425fe539-4d20-448c-adc7-8c872c3605d2")
        final CompletableFuture<T> res;

        @objid ("afb5698c-cc07-4ecf-b562-a1fd23c916ac")
        private final Callable<T> callable;

        @objid ("fdd4738f-f464-4702-9673-e313b7676519")
        protected  UnoCallback(final Callable<T> r) {
            this.callable = r;
            this.res = new CompletableFuture<>();
            
        }

        /**
         * notifies the callback implementation
         * @param aData private data which was provided when the callback was requested.
         */
        @objid ("7e4ff865-6604-4499-8b14-f6b9062f3d33")
        @Override
        public void notify(final Object aData) {
            if (!this.res.isCancelled()) {
                try {
                    T result = this.callable.call();
                    this.res.complete(result);
                } catch (Throwable t) {
                    this.res.completeExceptionally(t);
                }
            }
            
        }

    }

}
