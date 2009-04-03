/*
 * JBoss, Home of Professional Open Source
 * Copyright 2005-2009, Red Hat Middleware LLC, and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.messaging.core.server;

import java.lang.management.ManagementFactory;

import javax.management.MBeanServer;

import org.jboss.messaging.core.config.Configuration;
import org.jboss.messaging.core.config.impl.ConfigurationImpl;
import org.jboss.messaging.core.logging.Logger;
import org.jboss.messaging.core.management.ManagementService;
import org.jboss.messaging.core.management.impl.ManagementServiceImpl;
import org.jboss.messaging.core.persistence.StorageManager;
import org.jboss.messaging.core.persistence.impl.journal.JournalStorageManager;
import org.jboss.messaging.core.persistence.impl.nullpm.NullStorageManager;
import org.jboss.messaging.core.remoting.server.RemotingService;
import org.jboss.messaging.core.remoting.server.impl.RemotingServiceImpl;
import org.jboss.messaging.core.security.JBMSecurityManager;
import org.jboss.messaging.core.security.impl.JBMSecurityManagerImpl;
import org.jboss.messaging.core.server.impl.MessagingServerImpl;

/**
 * A Messaging
 *
 * @author <a href="mailto:tim.fox@jboss.com">Tim Fox</a>
 * 
 * Created 24 Jan 2009 15:17:18
 *
 *
 */
public class Messaging
{
   private static final Logger log = Logger.getLogger(Messaging.class);

   public static MessagingServer newNullStorageMessagingServer()
   {
      return newNullStorageMessagingServer(new ConfigurationImpl());
   }

   public static MessagingServer newNullStorageMessagingServer(final Configuration config)
   {
      StorageManager storageManager = new NullStorageManager();

      RemotingService remotingService = new RemotingServiceImpl(config);

      JBMSecurityManager securityManager = new JBMSecurityManagerImpl();

      ManagementService managementService = new ManagementServiceImpl(ManagementFactory.getPlatformMBeanServer(),
                                                                      config.isJMXManagementEnabled());

      remotingService.setManagementService(managementService);

      MessagingServer server = new MessagingServerImpl();

      server.setConfiguration(config);

      server.setStorageManager(storageManager);

      server.setRemotingService(remotingService);

      server.setSecurityManager(securityManager);

      server.setManagementService(managementService);

      return server;
   }

   public static MessagingServer newMessagingServer(final Configuration config, final StorageManager storageManager)
   {
      RemotingService remotingService = new RemotingServiceImpl(config);

      JBMSecurityManager securityManager = new JBMSecurityManagerImpl();

      ManagementService managementService = new ManagementServiceImpl(ManagementFactory.getPlatformMBeanServer(),
                                                                      config.isJMXManagementEnabled());

      remotingService.setManagementService(managementService);

      MessagingServer server = new MessagingServerImpl();

      server.setConfiguration(config);

      server.setStorageManager(storageManager);

      server.setRemotingService(remotingService);

      server.setSecurityManager(securityManager);

      server.setManagementService(managementService);

      return server;
   }

   public static MessagingServer newNullStorageMessagingServer(final Configuration config, final MBeanServer mbeanServer)
   {
      StorageManager storageManager = new NullStorageManager();

      RemotingService remotingService = new RemotingServiceImpl(config);

      JBMSecurityManager securityManager = new JBMSecurityManagerImpl();

      ManagementService managementService = new ManagementServiceImpl(mbeanServer, config.isJMXManagementEnabled());

      remotingService.setManagementService(managementService);

      MessagingServer server = new MessagingServerImpl();

      server.setConfiguration(config);

      server.setStorageManager(storageManager);

      server.setRemotingService(remotingService);

      server.setSecurityManager(securityManager);

      server.setManagementService(managementService);

      return server;
   }

   public static MessagingServer newMessagingServer(final Configuration config)
   {
      StorageManager storageManager = new JournalStorageManager(config);

      RemotingService remotingService = new RemotingServiceImpl(config);

      JBMSecurityManager securityManager = new JBMSecurityManagerImpl();

      ManagementService managementService = new ManagementServiceImpl(ManagementFactory.getPlatformMBeanServer(),
                                                                      config.isJMXManagementEnabled());

      remotingService.setManagementService(managementService);

      MessagingServer server = new MessagingServerImpl();

      server.setConfiguration(config);

      server.setStorageManager(storageManager);

      server.setRemotingService(remotingService);

      server.setSecurityManager(securityManager);

      server.setManagementService(managementService);

      return server;
   }

   public static MessagingServer newMessagingServer(final Configuration config, final MBeanServer mbeanService)
   {
      StorageManager storageManager = new JournalStorageManager(config);

      RemotingService remotingService = new RemotingServiceImpl(config);

      JBMSecurityManager securityManager = new JBMSecurityManagerImpl();

      ManagementService managementService = new ManagementServiceImpl(mbeanService, config.isJMXManagementEnabled());

      remotingService.setManagementService(managementService);

      MessagingServer server = new MessagingServerImpl();

      server.setConfiguration(config);

      server.setStorageManager(storageManager);

      server.setRemotingService(remotingService);

      server.setSecurityManager(securityManager);

      server.setManagementService(managementService);

      return server;
   }
}
