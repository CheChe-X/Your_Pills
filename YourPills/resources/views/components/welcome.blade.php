<div class="p-6 lg:p-8 bg-white border-b border-gray-200">
    <img src="/images/your_pills.png" alt="">

    <h1 class="mt-8 text-2xl font-medium text-gray-900">
        Bem vindo ao Your Pills!
    </h1>

    <p class="mt-6 text-gray-500 leading-relaxed">
    Facilidade de acesso às receitas e medicamentos: Com a funcionalidade de guardar as receitas e a rotina de medicamentos no próprio telemóvel, o utilizador tem mais facilidade de acesso às informações, o que pode ser especialmente útil para idosos
    </p>
</div>
@if (auth()->user()->email === "admin@gmail.com")
<div class="bg-gray-200 bg-opacity-25 grid grid-cols-1 md:grid-cols-2 gap-6 lg:gap-8 p-6 lg:p-8">
    <div>
        <div class="flex items-center">
        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" class="w-6 h-6 stroke-gray-400"><path stroke="currentColor" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="m9.172 9.172-3.536 3.535a4 4 0 0 0 0 5.657v0a4 4 0 0 0 5.657 0l3.535-3.536M9.172 9.172l3.535-3.536a4 4 0 0 1 5.657 0v0a4 4 0 0 1 0 5.657l-3.536 3.535M9.172 9.172l5.656 5.656"/></svg>
            <h2 class="ml-3 text-xl font-semibold text-gray-900">
                <a href="#">Comprimidos</a>
            </h2>
        </div>

        <p class="mt-4 text-gray-500 text-sm leading-relaxed">
            Admin - Gestão de comprimidos
        </p>

        <p class="mt-4 text-sm">
            <a href="/crudcomprimidos/create" class="inline-flex items-center font-semibold text-indigo-700">
                Criar Comprimidos

                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" class="ml-1 w-5 h-5 fill-indigo-500">
                    <path fill-rule="evenodd" d="M5 10a.75.75 0 01.75-.75h6.638L10.23 7.29a.75.75 0 111.04-1.08l3.5 3.25a.75.75 0 010 1.08l-3.5 3.25a.75.75 0 11-1.04-1.08l2.158-1.96H5.75A.75.75 0 015 10z" clip-rule="evenodd" />
                </svg>
            </a>
        </p>
        
    </div>

    <div>
        <div class="flex items-center">
           
           
            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" class="w-6 h-6 stroke-gray-400">
                <path stroke-linecap="round" stroke-linejoin="round" d="M12 6.042A8.967 8.967 0 006 3.75c-1.052 0-2.062.18-3 .512v14.25A8.987 8.987 0 016 18c2.305 0 4.408.867 6 2.292m0-14.25a8.966 8.966 0 016-2.292c1.052 0 2.062.18 3 .512v14.25A8.987 8.987 0 0018 18a8.967 8.967 0 00-6 2.292m0-14.25v14.25" />
            </svg>
            <h2 class="ml-3 text-xl font-semibold text-gray-900">
                <a href="#">Receitas</a>
            </h2>
        </div>

        <p class="mt-4 text-gray-500 text-sm leading-relaxed">
        Admin - Gestão de receitas
        </p>

        <p class="mt-4 text-sm">
            <a href="https://laracasts.com" class="inline-flex items-center font-semibold text-indigo-700">
                Criar Receitas

                <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" class="ml-1 w-5 h-5 fill-indigo-500">
                    <path fill-rule="evenodd" d="M5 10a.75.75 0 01.75-.75h6.638L10.23 7.29a.75.75 0 111.04-1.08l3.5 3.25a.75.75 0 010 1.08l-3.5 3.25a.75.75 0 11-1.04-1.08l2.158-1.96H5.75A.75.75 0 015 10z" clip-rule="evenodd" />
                </svg>
            </a>
        </p>
    </div>
    @endif

    
</div>
