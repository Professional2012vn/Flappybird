USE [master]
GO
/****** Object:  Database [Flappybird]    Script Date: 14/12/2017 2:13:05 SA ******/
CREATE DATABASE [Flappybird]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'Flappybird', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.KIM\MSSQL\DATA\Flappybird.mdf' , SIZE = 5120KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'Flappybird_log', FILENAME = N'C:\Program Files\Microsoft SQL Server\MSSQL12.KIM\MSSQL\DATA\Flappybird_log.ldf' , SIZE = 3840KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [Flappybird].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [Flappybird] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [Flappybird] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [Flappybird] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [Flappybird] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [Flappybird] SET ARITHABORT OFF 
GO
ALTER DATABASE [Flappybird] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [Flappybird] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [Flappybird] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [Flappybird] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [Flappybird] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [Flappybird] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [Flappybird] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [Flappybird] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [Flappybird] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [Flappybird] SET  DISABLE_BROKER 
GO
ALTER DATABASE [Flappybird] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [Flappybird] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [Flappybird] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [Flappybird] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [Flappybird] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [Flappybird] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [Flappybird] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [Flappybird] SET RECOVERY FULL 
GO
ALTER DATABASE [Flappybird] SET  MULTI_USER 
GO
ALTER DATABASE [Flappybird] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [Flappybird] SET DB_CHAINING OFF 
GO
ALTER DATABASE [Flappybird] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [Flappybird] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
EXEC sys.sp_db_vardecimal_storage_format N'Flappybird', N'ON'
GO
USE [Flappybird]
GO
/****** Object:  Table [dbo].[Highscore]    Script Date: 14/12/2017 2:13:06 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Highscore](
	[Maso] [int] IDENTITY(1,1) NOT NULL,
	[Name] [nvarchar](50) NOT NULL,
	[Score] [int] NOT NULL,
 CONSTRAINT [PK_User] PRIMARY KEY CLUSTERED 
(
	[Maso] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

GO
SET IDENTITY_INSERT [dbo].[Highscore] ON 

INSERT [dbo].[Highscore] ([Maso], [Name], [Score]) VALUES (10468, N'Duy', 8)
INSERT [dbo].[Highscore] ([Maso], [Name], [Score]) VALUES (10469, N'Dương', 8)
INSERT [dbo].[Highscore] ([Maso], [Name], [Score]) VALUES (10467, N'Giang', 8)
INSERT [dbo].[Highscore] ([Maso], [Name], [Score]) VALUES (16821, N'Hiếu', 10)
INSERT [dbo].[Highscore] ([Maso], [Name], [Score]) VALUES (17050, N'Hoa', 1)
INSERT [dbo].[Highscore] ([Maso], [Name], [Score]) VALUES (17133, N'Hoa', 2)
INSERT [dbo].[Highscore] ([Maso], [Name], [Score]) VALUES (16822, N'Nam', 1)
INSERT [dbo].[Highscore] ([Maso], [Name], [Score]) VALUES (10465, N'Nước', 10)
INSERT [dbo].[Highscore] ([Maso], [Name], [Score]) VALUES (16819, N'Sáng', 9)
SET IDENTITY_INSERT [dbo].[Highscore] OFF
SET ANSI_PADDING ON

GO
/****** Object:  Index [myUniqueConstraint]    Script Date: 14/12/2017 2:13:06 SA ******/
ALTER TABLE [dbo].[Highscore] ADD  CONSTRAINT [myUniqueConstraint] UNIQUE NONCLUSTERED 
(
	[Name] ASC,
	[Score] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, IGNORE_DUP_KEY = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
GO
USE [master]
GO
ALTER DATABASE [Flappybird] SET  READ_WRITE 
GO
