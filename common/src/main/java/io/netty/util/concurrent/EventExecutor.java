/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.netty.util.concurrent;

/**
 * The {@link EventExecutor} is a special {@link EventExecutorGroup} which comes
 * with some handy methods to see if a {@link Thread} is executed in a event loop.
 * Besides this, it also extends the {@link EventExecutorGroup} to allow for a generic
 * way to access methods.
 *
 * EventExecutor是一个特殊的EventExecutorGroup，EventExecutor持有一些查看在事件
 * 循环中是否线程被执行的一些便利方法。除此之外，EventExecutor继承自EventExecutorGroup
 * 使用通用的方法访问方法。
 *
 */
public interface EventExecutor extends EventExecutorGroup {

    /**
     * Returns a reference to itself.
     * 返回引用本身
     */
    @Override
    EventExecutor next();

    /**
     * Return the {@link EventExecutorGroup} which is the parent of this {@link EventExecutor},
     * 返回当前EventExecutor的父EventExecutorGroup引用
     */
    EventExecutorGroup parent();

    /**
     * Calls {@link #inEventLoop(Thread)} with {@link Thread#currentThread()} as argument
     *该方法会调用inEventLoop(Thread thread)，使用Thread#currentThread()作为参数
     */
    boolean inEventLoop();

    /**
     * Return {@code true} if the given {@link Thread} is executed in the event loop,
     * {@code false} otherwise.
     * 如果给定的线程在事件循环中被执行，返回true，否则返回false
     */
    boolean inEventLoop(Thread thread);

    /**
     * Return a new {@link Promise}.
     * 返回一个新的Promise引用
     */
    <V> Promise<V> newPromise();

    /**
     * Create a new {@link ProgressivePromise}.
     * 创建一个新的ProgressivePromise对象
     */
    <V> ProgressivePromise<V> newProgressivePromise();

    /**
     * Create a new {@link Future} which is marked as succeeded already. So {@link Future#isSuccess()}
     * will return {@code true}. All {@link FutureListener} added to it will be notified directly. Also
     * every call of blocking methods will just return without blocking.
     *
     * 创建一个早已被标记为成功的Future。调用Future的isSuccess()将会返回true。添加到该对象的所有FutureListener
     * 将会被直接通知。每个阻塞方法的调用将直接不阻塞返回。
     */
    <V> Future<V> newSucceededFuture(V result);

    /**
     * Create a new {@link Future} which is marked as failed already. So {@link Future#isSuccess()}
     * will return {@code false}. All {@link FutureListener} added to it will be notified directly. Also
     * every call of blocking methods will just return without blocking.
     *
     * 创建一个早已被标记为失败的Future。调用Future的isSuccess()将会返回false。添加到该对象的所有FutureListener
     * 将会被直接通知。每个阻塞方法的调用将直接不阻塞返回。
     */
    <V> Future<V> newFailedFuture(Throwable cause);
}
