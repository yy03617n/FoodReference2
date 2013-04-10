CREATE TABLE IF NOT EXISTS `category` (
  `categoryid` int(11) NOT NULL auto_increment,
  `name` varchar(75) NOT NULL,
  `image` varchar(75) NOT NULL,
  PRIMARY KEY  (`categoryid`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;



INSERT INTO `category` (`categoryid`, `name`, `image`) VALUES
(1, 'Dairy', 'DairyImage'),
(2, 'Protein', 'ProteinImage'),
(3, 'Fats', 'FatsImage'),
(4, 'Fruits', 'FruitsImage'),
(5, 'Grains', 'GrainImage'),
(6, 'Vegetables', 'VegetablesImage');



CREATE TABLE IF NOT EXISTS `contains` (
  `foodid` int(11) NOT NULL,
  `contentid` int(11) NOT NULL,
  `amount` varchar(30) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;



CREATE TABLE IF NOT EXISTS `content` (
  `contentid` int(11) NOT NULL auto_increment,
  `name` varchar(75) NOT NULL,
  `idealintake` varchar(20) NOT NULL,
  `info` text NOT NULL,
  PRIMARY KEY  (`contentid`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;



INSERT INTO `content` (`contentid`, `name`, `idealintake`, `info`) VALUES
(1, 'Potassium', 'XX Grams', 'Found in bananas'),
(2, 'Fiber', 'XX Grams', 'Found in carrots'),
(3, 'Vitamin B', 'XX Grams', 'Found in green vegetation'),
(4, 'Vitamin C', 'XX Grams', 'Found in citrus fruits'),
(5, 'Vitamin D', 'XX Grams', 'Found in milk'),
(6, 'Vitamin A', 'XX Grams', 'Found in carrots'),
(7, 'Protein', 'XX Grams', 'Found in fish and poultry'),
(8, 'Omega', 'XX Grams', 'This is a good fat'),
(9, 'Calcium', 'XX Grams', 'Found in milk');



CREATE TABLE IF NOT EXISTS `food` (
  `foodid` int(11) NOT NULL auto_increment,
  `name` varchar(75) NOT NULL,
  `image` varchar(75) NOT NULL,
  `info` text NOT NULL,
  `categoryid` int(11) NOT NULL,
  PRIMARY KEY  (`foodid`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;



INSERT INTO `food` (`foodid`, `name`, `image`, `info`, `categoryid`) VALUES
(1, 'Milk', 'MildImage', 'Builds muscles', 1),
(2, 'Yogurt', 'YogurtImage', 'Contains good bacteria', 1),
(3, 'Cheese', 'CheeseImage', 'Not American or cream cheese', 1),
(4, 'Fish', 'FishImage', 'Contains protein', 2),
(5, 'Chicken', 'ChickenImage', 'Contains protein', 2),
(6, 'Nuts', 'NutsImage', 'Not peanuts', 2),
(7, 'Beans', 'BeansImage', 'Lima beans are especially good', 2),
(8, 'Eggs', 'EggsImage', 'Ideal in moderation', 2),
(9, 'Beef', 'BeefImage', 'Bad if served as fast food', 2),
(10, 'Grapes', 'GrapesImage', 'Good for heart', 4),
(11, 'Bananas', 'BananasImage', 'Source of potassium', 4),
(12, 'Oranges', 'OrangesImage', 'Source of Vitamin C', 4),
(13, 'Apples', 'ApplesImage', 'Come in varius types', 4),
(14, 'Broccoli', 'BroccoliImage', 'Best if served raw', 6),
(15, 'Lettuce', 'LettuceImage', 'The denser the better', 6),
(16, 'Tomatoes', 'TomatoesImage', 'Good to add health benefits to sandwich', 6),
(17, 'Potatoes', 'PotatoesImage', 'The skin is the healthiest part', 6),
(18, 'Carrots', 'CarrotsImage', 'Good for digestion', 6),
(19, 'Bread', 'BreadImage', 'Best if 100% whole wheat', 5),
(20, 'Oatmeal', 'OatmealImage', 'Do not add sugar', 5),
(21, 'Rice', 'RiceImage', 'Best if brown', 5),
(22, 'Pasta', 'PastaImage', 'Best if whole wheat', 5),
(23, 'Olive Oil', 'OliveOilImage', 'Helps clear arteries', 3);
