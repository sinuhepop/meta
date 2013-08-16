META
====

**META** brings Java to a new level using mainly *APT processors* and adding some *functional programming* capabilities. Well, maybe some day it will...

**Features:**

 - Metamodel representing all members of a class
   - Static access
   - Complex member hierarchy
 - Persistent collections
   - List, Vector, Tree, Map, Set
   - Implementing functional programming methods (map, filter, fold)
 - Immutability check
 - Interface extraction
 - Definition of constructors and static methods on interfaces and classes, which subclasses must implement
 - Warning when overriding or implementing a method without @Override
 - Warning on use of static mutable fields
 - And much more...

Try it with Maven:

	<dependencies>
		<dependency>
			<groupId>tk.spop</groupId>
			<artifactId>meta</artifactId>
			<version>0.0.1-SNAPSHOT</version>
		</dependency>
	</dependencies>

	<repositories>
		<repository>
			<id>meta</id>
			<url>https://raw.github.com/sinuhepop/meta/repo</url>
		</repository>
	</repositories>

