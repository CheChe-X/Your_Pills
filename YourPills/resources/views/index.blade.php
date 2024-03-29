<!DOCTYPE html>
<html lang="en">

<head>
  <!-- basic -->
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <!-- mobile metas -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="viewport" content="initial-scale=1, maximum-scale=1">
  <!-- site metas -->
  <title>YourPills</title>
  <meta name="keywords" content="">
  <meta name="description" content="">
  <meta name="author" content="">
  <!-- bootstrap css -->
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <!-- style css -->
  <link rel="stylesheet" href="css/style.css">
  <!-- Responsive-->
  <link rel="stylesheet" href="css/responsive.css">
  <!-- fevicon -->
  <link rel="icon" href="images/fevicon.png" type="image/gif" />
  <!-- Scrollbar Custom CSS -->
  <link rel="stylesheet" href="css/jquery.mCustomScrollbar.min.css">
  <!-- Tweaks for older IEs-->
  <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
  <!-- owl stylesheets -->
  <link rel="stylesheet" href="css/owl.carousel.min.css">
  <link rel="stylesheet" href="css/owl.theme.default.min.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.css" media="screen">
</head>

<body>
  <!-- header section start -->
  <div class="header_section">
    <nav class="destop_header navbar navbar-expand-lg navbar-light bg-light">
      <div class="logo"></div>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link" href="/">Página Principal</a>
          </li>


          </li>

          <li class="nav-item">
            <a class="nav-link" href="/about">Sobre Nós</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/contact">Contacto</a>
          </li>

          @if(Auth::user())
          <li class="nav-item">
            <a class="nav-link" href="/comprimidos">Comprimidos</a>
          <li class="nav-item">
            <a class="nav-link" href="/receitas">Receitas</a>
          </li>
            @if (auth()->user()->email === "admin@gmail.com")
            <li class="nav-item">
            <a class="nav-link" href="/dashboard">Administração</a>
            </li>
            @endif
          <li class="nav-item">
            <form method="POST" id="logout-form" action="{{ route('logout') }}" x-data>
              @csrf
              <a type="submit" class="nav-link" href="{{ route('logout') }}" @click.prevent="$root.submit();" onclick="event.preventDefault();document.getElementById('logout-form').submit();">{{ __('Log Out') }}</button>
              </a>
            </form>
          </li>
          @else
          <li class="nav-item active">
            <a class="nav-link" href="/login">Login</a>
          </li>
          @endif

        </ul>
      </div>
    </nav>
    <nav class="mobile_header navbar navbar-expand-lg navbar-light bg-light">
      <div class="logo"><a href="index.html"><img src="images/logo.png"></a></div>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent2" aria-controls="navbarSupportedContent2" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent2">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link" href="/">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/about">About</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="doctor.html">Doctor</a>
          </li>
          <li class="nav-item">
            <a class="logo_main" href="index.html"><img src="images/logo.png"></a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="depatments.html">Depatments</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="blog.html">Blog</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="/contact">Contact</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#"><img src="images/search-icon.png"></a>
          </li>
          <li class="nav-item active">
            <a class="nav-link" href="#">LOGIN</a>
          </li>
        </ul>
      </div>
    </nav>
  </div>
  <!-- header section end -->
  <!-- banner section start -->
  <div class="banner_section">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-6 padding_0">
          <div id="main_slider" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
              <div class="carousel-item active">
                <div class="banner_bg">
                  <div class="banner_taital_main">
                    <h1 class="banner_taital"> Reservatório de medicamentos e informação
                      <p class="long_text">Facilidade de acesso às receitas e medicamentos: Com a funcionalidade de guardar as receitas e a rotina de medicamentos no próprio telemóvel, o utilizador tem mais facilidade de acesso às informações, o que pode ser especialmente útil para idosos</p>
                      <div class="btn_main">
                        <div class="about_us"><a href="#">Sobre Nós</a></div>
                      </div>
                  </div>
                </div>
              </div>
              <div class="carousel-item">
                <div class="banner_bg">
                  <div class="banner_taital_main">
                    <h1 class="banner_taital">Gestão personalizado da rotina de medicamentos</h1>
                    <p class="long_text">Com a funcionalidade de criar a própria rotina de medicamentos, o utilizador tem mais controle sobre quando e como tomar seus medicamentos, o que pode ajudar a evitar esquecimentos ou erros na administração.</p>
                    <div class="btn_main">
                      <div class="about_us"><a href="#">Sobre Nós</a></div>
                    </div>
                  </div>
                </div>
              </div>

            </div>
            <a class="carousel-control-prev" href="#main_slider" role="button" data-slide="prev">
              <i class="fa fa-angle-left"></i></a>
            <a class="carousel-control-next" href="#main_slider" role="button" data-slide="next">
              <i class="fa fa-angle-right"></i>
            </a>
          </div>
        </div>
        <div class="col-md-6 padding_0">
          <div class="banner_img"></div>
        </div>
      </div>
    </div>
  </div>
  <!-- banner section end -->
  <!-- about section start -->
  <div class="about_section layout_padding">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-6">
          <div class="about_taital">
            <h4 class="about_text">Comprimidos</h4>
            <h1 class="highest_text">Comprimidos</h1>
            <p class="lorem_text">Com a funcionalidade de guardar comprimidos já podera os guardar na aplicação ou no site para assim sempre que perder a caixa ou se esquecer quandor for comprar basta só abrir a sua haba de comprimidos e eles estaram todos la</p>
            <div class="read_bt"><a href="Comprimidos.html">Comprimidos</a></div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="image_2" href="#"><img src="images/img-2.png"></div>
        </div>
      </div>
    </div>
  </div>
  <!-- about section end -->
  <!-- care section start -->
  <div class="care_section layout_padding">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-6">
          <div class="image_3" href="#"><img src="images/img-3.png"></div>
        </div>
        <div class="col-md-6">
          <div class="care_taital">
            <h1 class="care_text">Receitas</h1>
            <p class="ipsum_text">Com as receitas a pessoa terá a possibilidade de as guardar tal como os comprimidos para assim quando precisar delas basta abrir a aplicação e elas estaram todas la </p>
            <div class="read_bt_2"><a href="Receitas.html">Receitas</a></div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- care section end -->
  <!-- services section start -->

  <!-- services section end -->
  <!-- doctor section start -->
  <div class="doctor_section layout_padding">
    <div class="container">
      <div class="row">
        <div class="col-md-6 padding_top0">
          <h4 class="about_text">Rotinas</h4>
          <p class="lorem_text">Com as Rotinas terá a Possibelidade de finalmente marcar uma hora na sua aplicação ou no site para assim nunca se esquecer de as tomar</p>
          <div class="read_main">
            <div class="read_bt"><a href="#">Ver mais </a></div>
          </div>
        </div>
        <div class="col-md-6">
          <div class="image_4"><img src="images/img-4.png"></div>
        </div>
      </div>
    </div>
  </div>
  <!-- doctor section end -->
  <!-- contact section start -->
  <div class="contact_section layout_padding">
    <div class="container">
      <div class="row">
        <div class="col-md-6">
          <h1 class="contact_text">Entrar em Contacto</h1>
          <form action="/action_page.php">
            <div class="form-group">
              <input type="text" class="email-bt" placeholder="Nome" name="Name">
            </div>
            <div class="form-group">
              <input type="text" class="email-bt" placeholder="Email" name="Email">
            </div>
            <div class="form-group">
              <input type="text" class="email-bt" placeholder="Assunto" name="Email">
            </div>
            <div class="form-group">
              <textarea class="massage-bt" placeholder="Mensagem" rows="5" id="comment" name="text"></textarea>
            </div>
          </form>
          <div class="main_bt"><a href="#">Enviar </a></div>
        </div>
        <div class="col-md-6">
          <h1 class="contact_text">O que diz nossos amigos</h1>
          <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
              <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
              <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            </ol>
            <div class="carousel-inner">
              <div class="carousel-item active">
                <h2 class="selfideno_text">José</h2>
                <div class="client_main">
                  <div class="image_5"><img src="images/img-5.png"></div>
                  <p class="lorem_ipsum_text">Acho está ideia muito boa tem me ajudado muito principalmente a minha cara avô</p>
                  <div class="quote_icon"><img src="images/quote-icon.png"></div>
                </div>
              </div>
              <div class="carousel-item">
                <h2 class="selfideno_text">Alberto</h2>
                <div class="client_main">
                  <div class="image_5"><img src="images/img-5.png"></div>
                  <p class="lorem_ipsum_text">É pá está aplicação tem me ajudado imenso antes quase sempre que esquecia de tomar o comprimidos da manha mas depois de ter está aplicação nunca mais me esqueci deles</p>
                  <div class="quote_icon"><img src="images/quote-icon.png"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- contact section end -->
  <!-- appointment section start -->

  <!-- appointment section end -->
  <!-- footer section start -->
  <!-- info section -->
  <div class="info_section layout_padding">
    <div class="container ">
      <div class="info_content">
        <div>
          <div class="row">
            <div class="col-md-4">
              <br>
              <br>
              <br>
              <div class="d-flex">
                <h5 width="2000">
                  YourPills
                </h5>
              </div>
            </div>
            <div class="col-md-4">
              <div class="d-flex">
                <h5>
                  Companhias
                </h5>
              </div>
              <div class="d-flex ">
                <ul>
                  <li>
                    <a href="https://www.poch.portugal2020.pt/pt-pt/Paginas/default.aspx">
                      POCH
                    </a>
                  </li>
                  <li>
                    <a href="https://filipa-vilhena.edu.pt">
                      ESFV
                    </a>
                  </li>
                  <li>
                    <a href="http://tgpsi.filipa-vilhena.edu.pt">
                      Curso TGPSI
                    </a>
                  </li>
                </ul>
              </div>
            </div>
            <div class="col-md-4">
              <div class="d-flex">
                <h5>
                  Sobre Nós
                </h5>
              </div>
              <div class="d-flex ">
                <ul>
                  <li>
                    <a href="Comprimidos.html">
                      Comprimidos
                    </a>
                  </li>
                  <li>
                    <a href="Receitas.html">
                      Receitas
                    </a>
                  </li>
                  <li>
                    <a href="Rotinas.html">
                      Rotinas
                    </a>
                  </li>
                  <li>
                    <a href="contact.html">
                      Entre em Contacto connosco
                    </a>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!-- end info section -->
  <!-- footer section end -->
  <!-- copyright section start -->
  <div class="copyright_section">
    <div class="container">
      <p class="copyright"><img src="images/poch.png" width="500"></a></p>
    </div>
  </div>
  <!-- copyright section end -->
  <!-- Javascript files-->
  <script src="js/jquery.min.js"></script>
  <script src="js/popper.min.js"></script>
  <script src="js/bootstrap.bundle.min.js"></script>
  <script src="js/jquery-3.0.0.min.js"></script>
  <script src="js/plugin.js"></script>
  <!-- sidebar -->
  <script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
  <script src="js/custom.js"></script>
  <!-- javascript -->
  <script src="js/owl.carousel.js"></script>
  <script src="https:cdnjs.cloudflare.com/ajax/libs/fancybox/2.1.5/jquery.fancybox.min.js"></script>
</body>
<script>
  $(document).ready(function() {
    if (sessionStorage.getItem('phone_authenticated')) {
      $('.nav-item.active').show();
    }
  });
</script>

</html>