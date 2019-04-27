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
package io.netty.channel;

import io.netty.util.concurrent.EventExecutorGroup;

/**
 * Special {@link EventExecutorGroup} which allows registering {@link Channel}s that get
 * processed for later selection during the event loop.
 *
 * 一个特殊的EventExecutorGroup，允许注册的多个channel
 * 在事件循环期间针对后续的选择获得相应的处理
 *
 */
public interface EventLoopGroup extends EventExecutorGroup {
    /**
     * Return the next {@link EventLoop} to use
     *
     * 返回下一个要使用的EventLoop
     */
    @Override
    EventLoop next();

    /**
     * Register a {@link Channel} with this {@link EventLoop}. The returned {@link ChannelFuture}
     * will get notified once the registration was complete.
     *
     * 将一个Channel注册到EventLoop中。一旦注册完成，返回的ChannelFuture将会得到通知。
     */
    ChannelFuture register(Channel channel);

    /**
     * Register a {@link Channel} with this {@link EventLoop} using a {@link ChannelFuture}. The passed
     * {@link ChannelFuture} will get notified once the registration was complete and also will get returned.
     *
     *使用ChannelFuture（ChannelPromise继承ChannelFuture，
     * 其内部保存了channel对象的引用）来将Channel注册到当前的EventLoop中。一旦注册完成，那么
     * 传入的ChannelFuture将会得到通知并且也会返回。
     *
     */
    ChannelFuture register(ChannelPromise promise);

    /**
     * Register a {@link Channel} with this {@link EventLoop}. The passed {@link ChannelFuture}
     * will get notified once the registration was complete and also will get returned.
     *
     * @deprecated Use {@link #register(ChannelPromise)} instead.
     */
    @Deprecated
    ChannelFuture register(Channel channel, ChannelPromise promise);
}
